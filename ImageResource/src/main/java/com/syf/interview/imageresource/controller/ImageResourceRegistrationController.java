package com.syf.interview.imageresource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.UserModel;
import com.syf.interview.imageresource.service.UserService;

@RestController
public class ImageResourceRegistrationController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userModel) {
		userService.registerUser(userModel);
		return "User Registered Successfully";
		
	}
	
	@PostMapping(value = "/uploadImage" , consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public User uploadImage( @RequestPart("image") MultipartFile file) {
		User userJson = userService.uploadImageService(file);
		return userJson;
	}

}
