package com.example.cms.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.cms.enums.PostType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPost {

	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private int postId;
	
	@NotNull
	private String title;
	
	
	
	private String subTitle;
 
	@Size(min = 500,message = "The summary must be atleast of 500 words" )
	
	private String summary;
	
	private PostType postType;

//	private String seoTitle;
//
//	private String seoDescription;
//
//	private String[] seoTopics;
//	
	@Column ( updatable = false)
	private LocalDateTime createdAt;
 
	@CreatedBy
	private String createdby;
	
	@LastModifiedBy
	private String lastModifiedBy; 
	
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	
		@ManyToOne
private Blogs blogs;
}
