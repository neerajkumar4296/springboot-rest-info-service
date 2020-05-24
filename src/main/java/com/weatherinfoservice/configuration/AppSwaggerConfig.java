package com.weatherinfoservice.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppSwaggerConfig { 
	
	private static final String BASE_PACKAGE= "com.weatherinfoservice";
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))             
          .paths(PathSelectors.any())                        
          .build()
          .apiInfo(getApiInfo())
          .securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(basicAuthScheme()));                                          
    }
    
	
    
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Rest Info Spring Boot Restful Web Service")
                .description("Spring boot Restful web service for providing information like weather and some other fun stuff")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("NA")
                .version("2.0")
                .contact(new Contact("Neeraj Kumar", "", "kumar.neeraj566@gmail.com"))
                .build();
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.any())
                .build();
    }
    
    
    
    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("Basic Authorization");
    }
    
    private SecurityReference basicAuthReference() {
        return new SecurityReference("Basic Authorization", new AuthorizationScope[0]);
    }
    

}