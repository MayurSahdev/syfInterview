package com.syf.interview.imageresource.service;

import org.springframework.web.multipart.MultipartFile;

import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.UserModel;

public interface UserService {
	User registerUser(UserModel userModel);
	User uploadImageService(MultipartFile file);
}