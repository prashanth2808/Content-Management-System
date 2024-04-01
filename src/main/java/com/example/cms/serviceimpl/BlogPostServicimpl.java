package com.example.cms.serviceimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.cms.entity.BlogPost;
import com.example.cms.entity.User;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.request.BlogPostRequest;
import com.example.cms.response.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.ResponseStructure;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.Builder;

public class BlogPostServicimpl implements BlogPostService{

	private BlogRepository brepo;

	private BlogPostRepository bprepo;

	private ResponseStructure<BlogPostResponse> structure;


	public BlogPost maptoblog(BlogPostRequest breq)

	{
		return BlogPost.builder().createdAt(breq.getCreatedAt()).createdby(breq.getCreatedby()).title(breq.getTitle()).subTitle(breq.getSubTitle()).summary(breq.getSummary()).build();
	}


	public BlogPostResponse maptoblogresp(BlogPost bpost)
	{
		return BlogPostResponse.builder().title(bpost.getTitle()).subTitle(bpost.getSubTitle()).summary(bpost.getSummary()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> savetheblogpost(int blogid,BlogPostRequest bpreq)
	{

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return brepo.findById(blogid).map( t -> {  


			List<User> list = t.getPanel().getList();

			for (User user : list) {
				if(user.getEmail().equals(email) != false )
					throw new IllegalAccessRequestException("blog not found by given id");
			}
			BlogPost post = maptoblog(bpreq);
			post=	bprepo.save(post);
			return			ResponseEntity.ok(structure.setMessage(" ").setStatusCode(HttpStatus.OK.value()).setData(maptoblogresp(post)));

		}).orElseThrow(() -> new UserNotFoundByIdException("check the user id"));

	}

}

