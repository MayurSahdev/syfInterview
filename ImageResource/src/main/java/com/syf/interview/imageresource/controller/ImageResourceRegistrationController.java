package com.syf.interview.imageresource.controller;

import com.syf.interview.imageresource.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> registerUser(@RequestBody UserModel userModel) {
		User user = userService.registerUser(userModel);
		return ResponseEntity.ok("User " + user.getUsername() + " Registered Successfully");
	}

	@GetMapping("/publish")
	public ResponseEntity<String> publisMessagesOnKafka() {
		int numOfImages = userService.publishMessages();
		return ResponseEntity.ok(numOfImages + " Images has been published Successfully");
	}

}
