package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.entity.User;
import com.example.cms.request.UserRequest;
import com.example.cms.response.UserResponse;
import com.example.cms.util.ResponseStructure;

public interface UserService {

	
	public ResponseEntity<ResponseStructure<UserResponse>>  saveUser(UserRequest request);
	
	public ResponseEntity<ResponseStructure<UserResponse>>  finduserbyid(int id);
	
	public ResponseEntity<ResponseStructure<String>> deleteuser(int id);
	
}
