package com.syf.interview.imageresource.controller;

import com.syf.interview.imageresource.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

	@GetMapping("/publish")
	public String publisMessagesOnKafka() {
		userService.publishMessages();
		return "Images has been published Successfully";
	}

}
