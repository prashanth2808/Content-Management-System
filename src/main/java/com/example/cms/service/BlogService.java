package com.example.cms.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.example.cms.entity.Blogs;
import com.example.cms.entity.User;
import com.example.cms.request.BlogsRequest;
import com.example.cms.response.BlogResponse;
import com.example.cms.util.ResponseStructure;

public interface BlogService  {

	public ResponseEntity<ResponseStructure<BlogResponse>> createablog(BlogsRequest blogsreq,int id);

	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int id);
	
	public ResponseEntity<ResponseStructure<Boolean>> checkBlogTitleAvailable(String title);
}
