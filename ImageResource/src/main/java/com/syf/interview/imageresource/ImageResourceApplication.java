package com.syf.interview.imageresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ImageResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageResourceApplication.class, args);
	}
	
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info().title("Image Resource API").version("1"));
	}

}
