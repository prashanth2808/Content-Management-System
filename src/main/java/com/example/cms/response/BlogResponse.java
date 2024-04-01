package com.example.cms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BlogResponse {

	private int blogid;
	private String title;
	private String[] topics;
	private String about;

}
