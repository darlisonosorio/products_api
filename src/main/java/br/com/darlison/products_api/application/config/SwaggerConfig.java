package br.com.darlison.products_api.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                description = "Serviço de gestão de produtos e clientes",
                version = "1.0.0",
                title = "Serviço de Produtos"
        )
)
public class SwaggerConfig {
}