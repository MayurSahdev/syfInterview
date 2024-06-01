package com.syf.interview.imageresource.service;

import org.springframework.web.multipart.MultipartFile;

import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.ImageDetails;
import com.syf.interview.imageresource.model.UserModel;

public interface UserService {
	User registerUser(UserModel userModel);
	void uploadImageService(MultipartFile file);
	void updateImageTitle(ImageDetails imageDetails);
	String getImagePath();
	void deleteImageService(String imageName);
}
