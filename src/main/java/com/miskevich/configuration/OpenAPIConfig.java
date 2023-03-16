package com.miskevich.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.miskevich.constant.OpenApiConst.EVGENI_MISKEVICH_GMAIL_COM;
import static com.miskevich.constant.OpenApiConst.FOR_WORKING_WITH_PHONE_SERVICE;
import static com.miskevich.constant.OpenApiConst.MISKEVICH_YAUHENI;
import static com.miskevich.constant.OpenApiConst.SERVICE_API;
import static com.miskevich.constant.OpenApiConst.VERSION;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(SERVICE_API)
                .description(FOR_WORKING_WITH_PHONE_SERVICE)
                .version(VERSION)
                .contact(apiContact());

    }


    private Contact apiContact() {
        return new Contact()
                .name(MISKEVICH_YAUHENI)
                .email(EVGENI_MISKEVICH_GMAIL_COM);
    }

}