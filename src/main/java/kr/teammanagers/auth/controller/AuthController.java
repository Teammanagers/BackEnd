package kr.teammanagers.auth.controller;

import kr.teammanagers.global.provider.TokenProvider;
import kr.teammanagers.auth.dto.TokenDto;
import kr.teammanagers.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    @GetMapping("/login-success")
    public ResponseEntity<TokenDto> loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // OAuth2 로그인 성공 시 호출되는 엔드포인트
        String accessToken = tokenProvider.generateAccessToken(oAuth2User);
        tokenProvider.generateRefreshToken(oAuth2User, accessToken);

        return ResponseEntity.ok(new TokenDto(accessToken, null));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = request.getHeader("Authorization-refresh");
        String accessToken = request.getHeader("Authorization");

        if (refreshToken == null || !tokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.badRequest().build();
        }

        String newAccessToken = tokenProvider.reissueAccessToken(accessToken);
        if (newAccessToken == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(new TokenDto(newAccessToken, null));
    }
}
