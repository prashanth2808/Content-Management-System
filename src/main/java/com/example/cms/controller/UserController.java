package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.User;
import com.example.cms.service.UserService;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {


	private UserService service;

	@GetMapping("/test")
	public String test()
	{
		return "Hello from CMS";
	}

	
	
	@Operation (description = "The endpoint is used save the userdata ", responses = {@ApiResponse( responseCode = "200",description = "User saved"),
			@ApiResponse(responseCode = "400",description = "Invalid inputs",content =  @Content(schema = @Schema(implementation = ErrorStructure .class )   )  )	})
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid @RequestBody UserRequest    request  ){
		return service.saveUser(request );
	}

}
