package com.myapp.WishList.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${swagger.openapi.local-url}")
    private String localUrl;
    @Bean
    public OpenAPI myOpenAPI() {

        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Server at local environment");

        Contact contact = new Contact();
        contact.setEmail("alex_saraiva14@hotmail.com");
        contact.setName("Alexsandro S. Saraiva");
        contact.setUrl("https://www.linkedin.com/in/alexsandrosaraiva/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("WishList API")
                .version("1.0")
                .contact(contact)
                .description("API para consulta, remoção, adição de produtos a lista de desejos do cliente")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(localServer));
    }
}
