package br.inpe.dpi.terrabrasilis.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          //.apis(RequestHandlerSelectors.any())              
          .apis(RequestHandlerSelectors.basePackage("br.inpe.dpi.terrabrasilis.resource"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(this.apiInfo());                                           
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
          "TerraBrasilis Business REST API", 
          "Using this API you can request for all data like Visions, Layers, Tools, Datasources, Downloads and Subddomains", 
          "0.0.1", 
          "Terms of service", 
          new Contact("Jether Rodrigues", "http://terrabrasilis.dpi.inpe.br", "jetherrodrigues@gmail.com"), 
          "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}