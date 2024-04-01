package com.example.cms.response;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPostResponse {

	private int postId;
	
	private String title;
	
	private String subTitle;
	
    private String summary;
	
//	private String seoTitle;
//	
//	private String seoDescription;
//	
//	private String[] seoTopics;
//
	private String createdBy;

	private LocalDateTime createAt;
	
	private String lastModifiedBy;
	
	private LocalDateTime lastModifiedAt;
}
