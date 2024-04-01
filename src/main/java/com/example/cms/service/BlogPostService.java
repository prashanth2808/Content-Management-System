package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.request.BlogPostRequest;
import com.example.cms.response.BlogPostResponse;
import com.example.cms.util.ResponseStructure;

public interface BlogPostService {

	public ResponseEntity<ResponseStructure<BlogPostResponse>> savetheblogpost(int blogid,BlogPostRequest blogPostreq);
}
