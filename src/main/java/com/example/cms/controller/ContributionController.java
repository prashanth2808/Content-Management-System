package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.response.ContributionPanelResponse;
import com.example.cms.serviceimpl.ContributionServiceimpl;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContributionController {

	private ContributionServiceimpl service;
	
	
	@PutMapping ("/users/{userId}/contribution-panels/{panelId}")
	@Operation ( description = "The blog owner can add other users as contributors to the contribution panel, letting them write blog posts",responses = {@ApiResponse(responseCode = "200",
	description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
	content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributers(@PathVariable  int panelId,@PathVariable  int userId)
	{
		return service.addContributor(userId, panelId);
	}
	
	
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	@Operation ( description = "The blog owner can remove the user from the contribution panel",responses = {@ApiResponse(responseCode = "200",
	description = "Created and Saved successfully"),@ApiResponse(responseCode = "400",description = "Invalid inputs",
	content = @Content(schema = @Schema (implementation = ErrorStructure.class) )  )  })
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteablog(@PathVariable  int panelId,@PathVariable   int userId)
	
	{
		return service.deleteablog(panelId,userId);
	}
}
