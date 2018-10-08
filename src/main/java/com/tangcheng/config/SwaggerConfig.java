package com.tangcheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.tangcheng.controller")).build();
	}
	
	private ApiInfo apiInfo() {
		 return new ApiInfoBuilder().title("服务平台API").description("")
		 .termsOfServiceUrl("http:localhost:8080/tangcheng").version("1.0").build();
	}
}
