package com.syf.interview.imageresource.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.ImageDetails;
import com.syf.interview.imageresource.model.UserModel;
import com.syf.interview.imageresource.repository.UserRepository;
import com.syf.interview.imageresource.webclient.ImageResourceWebClient;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImageResourceWebClient imageResourceWebClient;

	@Override
	public User registerUser(UserModel userModel) {
		User user = User.builder().userName(userModel.getUserName()).password(userModel.getPassword())
				.email(userModel.getEmail()).build();
		userRepository.save(user);

		return user;
	}

	@Override
	public void uploadImageService(MultipartFile file) {
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("D:\\ImageOri.jpg")));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }}
		imageResourceWebClient.uploadImage(file);
	}

	@Override
	public String getImagePath() {
		System.out.println("I am in getImagePath");		
		return null;
	}

	@Override
	public void deleteImageService(String imageName) {
		imageResourceWebClient.deleteImage(imageName);
	}

	@Override
	public void updateImageTitle(ImageDetails imageDetails) {
		imageResourceWebClient.updateImageDetails(imageDetails.getTitle(), imageDetails.getDescription() , imageDetails.getImageName());
	}

}
