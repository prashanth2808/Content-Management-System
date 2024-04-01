package com.example.cms.serviceimpl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blogs;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepository;
import com.example.cms.request.BlogsRequest;
import com.example.cms.response.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.ResponseStructure;
import com.example.cms.util.TitleNotAvailablException;
import com.example.cms.util.TopicNotSpecifiedException;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor


public class BlogServiceImpl implements BlogService {


	private ResponseStructure<BlogResponse> structure;
	private BlogRepository brepo;
	private UserRepository urepo;
	private ResponseStructure<Boolean> booleanstructure;
	private ContributionPanelRepo crepo;

	public Blogs maptoblogs(BlogsRequest breq)
	{
		return Blogs.builder().title(breq.getTitle()).topics(breq.getTopics()).about(breq.getAbout()).build();
	}

	public BlogResponse maptoblogresponse(Blogs  blogs )
	{
		return BlogResponse.builder().title(blogs.getTitle()).about(blogs.getAbout()).topics(blogs.getTopics()).blogid(blogs.getBlogId() ).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createablog(BlogsRequest  breq ,int id)
	{	
		return urepo.findById(id)
				.map(p ->{
					if 
					(brepo.existsByTitle(breq.getTitle()))	
					{
						throw new TitleNotAvailablException(" failed to create blog");
					}
					if(breq.getTopics().length<1 )

						throw new TopicNotSpecifiedException("failed to create blog");

					Blogs blogs = maptoblogs(breq);
					ContributionPanel cp = crepo.save(new ContributionPanel());
				
					blogs.setPanel(cp);
					blogs.setUser(p);
					brepo.save(blogs);

					return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("Blog created").setData(maptoblogresponse(blogs)));
				}).orElseThrow(() -> new UserNotFoundByIdException("failed to create blogs"));
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int id) {
		return brepo.findById(id).map(t ->{   
			return 	ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("Blog found").setData(maptoblogresponse(t)));
		}).orElseThrow(()-> new BlogNotFoundException ("Error 404.Check for blog id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<Boolean>> checkBlogTitleAvailable(String title) {
		if(brepo.existsByTitle(title))
		{
			return ResponseEntity.ok(booleanstructure.setStatusCode(HttpStatus.OK.value()).setMessage("title is found").setData(true));
		}throw new TitleNotAvailablException("Title not available" );
	}

	public ResponseEntity<ResponseStructure<BlogResponse>> updateblog( BlogsRequest breq ,int blogId) {

		return brepo.findById(blogId).map(t ->  {    

			Blogs blogs = maptoblogs(breq);
			blogs.setBlogId(blogId);	
			brepo.save( blogs);
			return ResponseEntity.ok(structure.setMessage(" " ).setStatusCode(HttpStatus.OK.value()).setData(maptoblogresponse(blogs)));

		}).orElseThrow(() -> new BlogNotFoundException("check for id") ); 
	}
}


