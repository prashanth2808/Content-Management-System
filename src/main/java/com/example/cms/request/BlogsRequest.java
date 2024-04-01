package com.example.cms.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class BlogsRequest {

	
	@NotNull
	@Pattern(regexp="^[a-zA-Z]+$",message = "Invalid Input")
	private String title;
	@NotNull
	private String[] topics;

	private String about;
}