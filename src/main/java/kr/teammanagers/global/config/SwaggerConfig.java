package kr.teammanagers.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static kr.teammanagers.global.constant.TokenKey.TOKEN_PREFIX;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("OpenAPI") String appVersion, SecurityConfig securityConfig) {
        Server prodServer = new Server().url(securityConfig.getBackEndUrl()).description("운영 서버");
        Server localServer = new Server().url("http://localhost:8080").description("로컬 서버");
        Info info = new Info().title("Team Managers API").version(appVersion)
                .description("Team Managers API 입니다.")
                .termsOfService(securityConfig.getBackEndUrl())
                .contact(new Contact().name("TeamManagers").email("teammanagers@gmail.com"))
                .license(new License().name("Apache License Version 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0"));

        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme(TOKEN_PREFIX)
                                                .bearerFormat("JWT")
                                )
                )
                .info(info)
                .servers(List.of(prodServer, localServer));
    }
}
