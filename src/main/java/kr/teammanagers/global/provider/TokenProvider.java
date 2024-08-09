package kr.teammanagers.global.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import kr.teammanagers.auth.Application.TokenService;
import kr.teammanagers.auth.dto.PrincipalDetails;
import kr.teammanagers.auth.jwt.domain.MemberToken;
import kr.teammanagers.global.exception.TokenException;
import kr.teammanagers.member.domain.Member;
import kr.teammanagers.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static kr.teammanagers.global.exception.ErrorCode.INVALID_JWT_SIGNATURE;
import static kr.teammanagers.global.exception.ErrorCode.INVALID_TOKEN;

@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("${TOKEN_SECRET}")
    private String secretKeyString;
    private SecretKey secretKey;

    @Value("${ACCESS_TOKEN_EXPIRATION}")
    private long accessTokenExpireTime;

    @Value("${REFRESH_TOKEN_EXPIRATION}")
    private long refreshTokenExpireTime;

    private static final String KEY_ROLE = "role";
    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, accessTokenExpireTime);
    }

    public void generateRefreshToken(Authentication authentication, String accessToken) {
        String refreshToken = generateToken(authentication, refreshTokenExpireTime);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        tokenService.saveOrUpdate(getUsername(authentication), refreshToken, accessToken);
    }

    private String generateToken(Authentication authentication, long expireTime) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expireTime);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        String username = getUsername(authentication);

        return Jwts.builder()
                .setSubject(username)
                .claim(KEY_ROLE, authorities)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        Member member = memberRepository.findByProviderId(claims.getSubject())
                .orElseThrow(RuntimeException::new);
        PrincipalDetails principal = PrincipalDetails.builder()
                .member(member)
                .build();
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        return Collections.singletonList(new SimpleGrantedAuthority(
                claims.get(KEY_ROLE).toString()));
    }

    public String reissueAccessToken(String accessToken) {
        if (StringUtils.hasText(accessToken)) {
            MemberToken token = tokenService.getTokenByAccessToken(accessToken)
                    .orElseThrow(() -> new TokenException(INVALID_TOKEN));
            String refreshToken = token.getRefreshToken();

            if (validateToken(refreshToken)) {
                String reissuedAccessToken = generateAccessToken(getAuthentication(refreshToken));
                tokenService.saveOrUpdate(token.getMember().getProviderId(), refreshToken, reissuedAccessToken);
                return reissuedAccessToken;
            }
        }
        return null;
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return claims.getExpiration().after(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).build()
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (MalformedJwtException e) {
            throw new TokenException(INVALID_TOKEN);
        } catch (SecurityException e) {
            throw new TokenException(INVALID_JWT_SIGNATURE);
        }
    }

    // authentication.getPrincipal();시 가져오는 클래스형에 따라 형변환 진행 후 ProviderId를 리턴.
    private String getUsername(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof PrincipalDetails) {
            return ((PrincipalDetails) principal).getUsername();
        } else if (principal instanceof User) {
            return ((User) principal).getUsername();
        } else {
            throw new IllegalArgumentException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}