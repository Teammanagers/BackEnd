package kr.teammanagers.global.config;

import kr.teammanagers.auth.handler.OAuth2FailureHandler;
import kr.teammanagers.auth.handler.OAuth2SuccessHandler;
import kr.teammanagers.auth.Application.CustomOAuth2UserService;
import kr.teammanagers.global.exception.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/error", "/favicon.ico");
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(
                        FrameOptionsConfig::disable).disable())
                .sessionManagement(c ->
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/auth/success"),
                                        new AntPathRequestMatcher("/funding-products/**", "GET"),
                                        new AntPathRequestMatcher("/notification/subscribe"),
                                        new AntPathRequestMatcher("/login/oauth2/code/**"),
                                        new AntPathRequestMatcher("/oauth2/**"),
                                        new AntPathRequestMatcher("/health")

                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth ->
                        oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                                .successHandler(oAuth2SuccessHandler)
                                .failureHandler(new OAuth2FailureHandler())
                )

                .addFilterBefore(tokenAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

                /*.addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass())

                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));*/

        return http.build();
    }
}