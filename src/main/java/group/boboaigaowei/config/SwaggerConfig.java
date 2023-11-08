package group.boboaigaowei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "Bearer"
)
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Snack-Overflow")
						.description("Snack-Overflow Test")
						.version("v1.0.0")
				)
				.externalDocs(new ExternalDocumentation()
						.description("Snack-Overflow side project docs")
						.url("https://rhetorical-cheese-1ce.notion.site/8afc540cea5d4fa29a919590e9565af6?v=d5a0bc50ca884d809b53defde6fe7ec8&pvs=4")
				);
	}
	    
//    @Bean
//    public GroupedOpenApi loginApi() {
//        String[] packagesToScan = {"group.boboaigaowei"};
//        
//        return GroupedOpenApi.builder()
//        	.displayName("Snack-Overflow Test")
//            .group("loginApi")
//            .packagesToScan(packagesToScan)
//            .pathsToMatch("/login/**")
//            .build();
//    }
}
