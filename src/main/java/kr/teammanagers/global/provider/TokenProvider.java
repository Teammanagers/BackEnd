package kr.teammanagers.global.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import jakarta.annotation.PostConstruct;

import kr.teammanagers.auth.jwt.domain.MemberToken;
import kr.teammanagers.auth.service.TokenService;
import kr.teammanagers.global.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, accessTokenExpireTime);
    }

    public void generateRefreshToken(Authentication authentication, String accessToken) {
        String refreshToken = generateToken(authentication, refreshTokenExpireTime);
        tokenService.saveOrUpdate(authentication.getName(), refreshToken, accessToken);
    }

    private String generateToken(Authentication authentication, long expireTime) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expireTime);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(KEY_ROLE, authorities)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);

        User principal = new User(claims.getSubject(), "", authorities);
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
                tokenService.saveOrUpdate(token.getMember().getId().toString(), refreshToken, reissuedAccessToken);
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
}