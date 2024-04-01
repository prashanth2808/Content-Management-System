package com.example.cms.serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.request.UserRequest;
import com.example.cms.response.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ResponseStructure;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


	private ResponseStructure<UserResponse> structure;
	private UserRepository repo;
	private PasswordEncoder encoder;
	private ResponseStructure<String> stringstructure;

	//mapping userrequest to user,getting from userreq and setting to user w help of builder
	//user req -> user

	private User maptouserreq( UserRequest  request)  
	{
		return 	User.builder().username(request.getUsername()).email(request.getEmail()).password(encoder.encode(request.getPassword())).deleteuser(false).build();
	} 

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//mapping user to useresponse,getting from user and setting to userresponse w help of builder
	//user -> user response
	private UserResponse maptouser(User user)
	{
		return UserResponse.builder().userid(user.getUserId()).email(user.getEmail()).username(user.getUsername()).createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt()).build();
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser( UserRequest request) {
		if(repo.existsByEmail(request.getEmail()) )
			throw new DuplicateEmailException("Email already exist");

		User user = maptouserreq(request);
		user = repo.save(user);


		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("User saved").setData(maptouser(user)));
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteuser(int id) {
		return repo.findById(id).map(t-> { 
			t.setDeleteuser(true);
			repo.save(t);
			return ResponseEntity.ok(stringstructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("User temprovorily deactiveted").setData("User deactivated you cant activated it again "));
		}).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
	}


	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> finduserbyid(int id) {

		User user = repo.findById(id).get();

		if(user.getDeleteuser()== false)
		{
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					.setMessage("User found")
					.setData(maptouser(user)));
		}

		throw new UsernameNotFoundException("User not found");
	}
}
