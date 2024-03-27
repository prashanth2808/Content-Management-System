package com.example.cms.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;



@Configuration
@OpenAPIDefinition
public class APIDocumentiation {
	
	Contact contact()
	{
		return new Contact().name("Amith")
				.email("amith@gmail.com")
				.url("www.amithdev.com");
	}

	
	@Bean
	Info info()
	{
		return new Info().description(" ")
				.title(" ")
				.version("1.0").contact(contact());
	}

	@Bean
  OpenAPI openAPI()
  {
	  return new OpenAPI().info(info() );
			  
  }
	

}
