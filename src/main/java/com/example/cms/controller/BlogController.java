package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.Blogs;
import com.example.cms.request.BlogsRequest;
import com.example.cms.response.BlogResponse;
import com.example.cms.serviceimpl.BlogServiceImpl;
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
public class BlogController {

	private BlogServiceImpl service;

	@PostMapping("/users/{userId}/blogs")
	@Operation ( description = "This Method is used to create a Blog and save for a User based on id",responses = {@ApiResponse(responseCode = "200",
	description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
	content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	public ResponseEntity<ResponseStructure<BlogResponse>>  createablog( @Valid @RequestBody  BlogsRequest request,@PathVariable int userId)
	{
		return service.createablog(request,userId);
	}

	@Operation ( description = "This Method is used to find a Blog and based on id",responses = {@ApiResponse(responseCode = "200",
			description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
			content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	@GetMapping ("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>>  findBlogById(@PathVariable int blogId)
	{
		return service.findBlogById(blogId);
	}

	@Operation ( description = "This Method is used to check the title og blog based on id",responses = {@ApiResponse(responseCode = "200",
			description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
			content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<ResponseStructure<Boolean>> checkBlogTitleAvailable( @PathVariable  String title) {
		return service.checkBlogTitleAvailable(title);
	}
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>>  updateblog ( @PathVariable  int blogId,@RequestBody  BlogsRequest breq) {
		return service.updateblog(breq, blogId);
	}
}