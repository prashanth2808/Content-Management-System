package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.request.BlogPostRequest;
import com.example.cms.response.BlogPostResponse;
import com.example.cms.serviceimpl.BlogPostServicimpl;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BlogPostController {

	private BlogPostServicimpl service;


	@Operation ( description = "This Method is used to save the blogpost as draft",responses = {@ApiResponse(responseCode = "200",
			description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
			content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> savetheblogpost(@RequestBody BlogPostRequest postreq , int blogId)
	{
		return service.savetheblogpost(blogId,postreq);

	}
}
