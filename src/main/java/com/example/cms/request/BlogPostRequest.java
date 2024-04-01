package com.example.cms.request;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.cms.enums.PostType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPostRequest {

	private String title;
	
	private String subTitle;
 
	private String summary;
	
	private PostType postType;

	private String seoTitle;

	private String seoDescription;

	private String[] seoTopics;
	
	private LocalDateTime createdAt;
 
	private String createdby;
	
	private String lastModifiedBy; 
	
	private LocalDateTime lastModifiedAt;
	
}
