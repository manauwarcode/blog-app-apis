package com.blog.BackendBlogApplicationAPIs.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private ApiKey apiKeys(){
//        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
//    }
//
//    private List<SecurityContext> securityContexts(){
//        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
//    }
//
//    private List<SecurityReference> securityReferences(){
//        AuthorizationScope scopes = new AuthorizationScope("global","accessEverything");
//        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scopes}));
//    }
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .securityContexts(securityContexts())
//                .securitySchemes(Arrays.asList(apiKeys()))
//                .select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()).build();
//    }

}
