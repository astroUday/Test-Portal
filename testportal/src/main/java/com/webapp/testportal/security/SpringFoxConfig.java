//package com.webapp.testportal.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//@Configuration
//public class SpringFoxConfig {
////    public static final String AUTHORIZATION_HEADER="Authorization";
////    private ApiKey apiKeys(){
////        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
////    }
////
////    private List<SecurityContext> securityContexts(){
////        return (List<SecurityContext>) SecurityContext.builder().securityReferences(securityReferences()).build();
////    }
////
////    private List<SecurityReference> securityReferences(){
////
////        AuthorizationScope scope=new AuthorizationScope("global","accesseverything");
////        return (List<SecurityReference>) new SecurityReference("JWT", new AuthorizationScope[]{scope});
////    }
//    @Bean
//    public Docket api() {
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build().securityContexts(securityContexts()).securitySchemes((List<SecurityScheme>) apiKeys());
//    }
////    private ApiInfo metaData() {
////        return new ApiInfoBuilder()
////                .title("Tech Interface - Spring Boot Swagger Configuration")
////                .description("\"Swagger configuration for application \"")
////                .version("1.1.0")
////                .license("Apache 2.0")
////                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
////                .contact(new Contact("Tech Interface", "https://www.youtube.com/channel/UCMpJ8m1w9t7EFcF9x8rs02A", "info@techinterface.com"))
////                .build();
////    }
//}
