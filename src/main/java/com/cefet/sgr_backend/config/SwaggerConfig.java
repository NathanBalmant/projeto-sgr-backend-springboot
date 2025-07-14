package com.cefet.sgr_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("API da República")
            .version("1.0")
            .description("Documentação da API de gestão de contas dos moradores da república."));
    }
    //primeira versão do swagger
}