package com.syf.interview.imageresource.service;

import org.springframework.web.multipart.MultipartFile;

import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.ImageMetaData;
import com.syf.interview.imageresource.model.UserModel;

public interface UserService {
	User registerUser(UserModel userModel);
	void uploadImageService(MultipartFile file);
	void updateImageTitle(ImageMetaData imageMetaData);
	String getImage(String imageName);
	void deleteImageService(String imageName);
	void publishMessages();
}
