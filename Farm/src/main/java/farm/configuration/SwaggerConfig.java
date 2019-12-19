package farm.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// swagger link: http://localhost:8080/swagger-ui.html#/
	
	@Bean
	public Docket postsApi() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2);

		docket.groupName("public-api")
			.apiInfo(apiInfo())
	        .securitySchemes(Collections.singletonList(apiKey()))
	        .enable(true)
			.select()
			.paths(postPaths())
			.build();


		docket.globalResponseMessage(RequestMethod.GET, ImmutableList.of(
				new ResponseMessageBuilder()
				.code(400)
				.message("Bad Request")
				.responseModel(new ModelRef("Error")).build(),
				new ResponseMessageBuilder()
				.code(500)
				.message("Internal Server Error")
				.responseModel(new ModelRef("Error")).build(),
				new ResponseMessageBuilder()
				.code(404)
				.message("Not Found")
				.responseModel(new ModelRef("Error")).build()));

		return docket;
	}
	
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Bearer", "header");
    }

	private Predicate<String> postPaths() {
		return or(regex("/animals.*"), regex("/cow.*"), regex("/bulls.*"), regex("/groups.*"), regex("/users.*"), regex("/user.*"), regex("/login.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Farm API")
				.description("Farm API reference for developers")
				.termsOfServiceUrl("http://localhost.com")
				.contact("oleg.gumush@gmail.com").license("Farm License")
				.licenseUrl("oleg.gumush@gmail.com").version("1.0").build();
	}
}