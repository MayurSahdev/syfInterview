package com.syf.interview.imageresource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.UserModel;
import com.syf.interview.imageresource.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(UserModel userModel) {
		User user = User.builder().userName(userModel.getUserName()).password(userModel.getPassword())
				.email(userModel.getEmail()).build();
		userRepository.save(user);

		return user;
	}

	@Override
	public User uploadImageService(MultipartFile file) {
		System.out.println("I am in Multipart File");
		return null;
		
	}

}
