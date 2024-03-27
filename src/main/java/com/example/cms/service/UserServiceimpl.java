package com.example.cms.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {


	private ResponseStructure<UserResponse> structure;
	private UserRepository repo;
	private PasswordEncoder encoder;

	private User maptouserreq(User user, UserRequest  request)  
	{

		return 	User.builder().username(request.getUsername()).email(request.getEmail()).password(encoder.encode(request.getPassword())).build();
	} 

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	private UserResponse maptouser(User user)
	{
		return UserResponse.builder().userid(user.getUserId()).email(user.getEmail()).username(user.getUsername()).createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt()).build();
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser( UserRequest request) {
		if(repo.existsByEmail(request.getEmail()) )
			throw new DuplicateEmailException("Email already exist");

		User user = maptouserreq(new User(),request);
		user = repo.save(user);


		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("User saved").setData(maptouser(user)));
	}

//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> finduserbyid(int id) {
//
//	Optional<User> findById = repo.findById(id);
//	
	
	}


	
	
	
