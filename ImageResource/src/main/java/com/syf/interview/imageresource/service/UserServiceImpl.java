package com.syf.interview.imageresource.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.syf.interview.imageresource.entity.ImageDetail;
import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.kafka.KafkaProducer;
import com.syf.interview.imageresource.model.ImageMetaData;
import com.syf.interview.imageresource.model.ResponseData;
import com.syf.interview.imageresource.model.UserModel;
import com.syf.interview.imageresource.repository.ImageDetailRepository;
import com.syf.interview.imageresource.repository.UserRepository;
import com.syf.interview.imageresource.webclient.ImageResourceWebClient;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImageDetailRepository imageDetailRepository;

	@Autowired
	ImageResourceWebClient imageResourceWebClient;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private KafkaProducer kafkaProducer;

	/**
	 * Method to register a new User.it'll create a entry in Database.
	 */
	@Override
	public User registerUser(UserModel userModel) {
		User user = User.builder().userName(userModel.getUserName())
				.password(passwordEncoder.encode(userModel.getPassword())).build();
		userRepository.save(user);
		LOGGER.debug("User is registered with username : " + userModel.getUserName());
		return user;
	}

	/**
	 * Method to call Imgur API and create the image. Entry will be created is Database.
	 */
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
			}
		}
		
		
		ResponseData result = imageResourceWebClient.uploadImage(file);
		Date currentDate = new Date();
		ImageDetail imageDetail = ImageDetail.builder().imageName(result.getData().getId())
				.userName(result.getData().getAccount_url())
				.createdDate(currentDate).build();
		imageDetailRepository.save(imageDetail);
		LOGGER.debug("New Image is updated in Imgur API and entry has been created in Database");

	}
	
	/**
	 * Method to call Imgur API and get the clickable link of request image.
	 */
	@Override
	public String getImage(String imageName) {
		ResponseData data = imageResourceWebClient.getImage(imageName);
		return data.getData().getLink();
	}

	/**
	 * Method to delete the image from Imgur API as well as entry from Database.
	 */
	@Override
	public void deleteImageService(String imageName) {
		imageResourceWebClient.deleteImage(imageName);
		ImageDetail imageDetail = imageDetailRepository.findByImageName(imageName);
		imageDetailRepository.delete(imageDetail);
	}

	/**
	 * Method to update the metadata of requested image.
	 */
	@Override
	public void updateImageTitle(ImageMetaData imageMetaData) {
		imageResourceWebClient.updateImageDetails(imageMetaData.getTitle(), imageMetaData.getDescription(),
				imageMetaData.getImageName());
	}

	/**
	 * Method will trigger an event for kafka which will publish all the Image name with associated user.
	 */
	@Override
	public void publishMessages() {
		
		// get all of the image details from Database
		List<ImageDetail> imageList = imageDetailRepository.findAll();
		
		// iterate through list and publish 1 by 1. 
		// for more RPM use batch publishing. 
		for (ImageDetail imageDetail : imageList) {
			Message<ImageDetail> message = MessageBuilder.withPayload(imageDetail).setHeader(KafkaHeaders.TOPIC, "userDetails").build();
			kafkaProducer.sendMessage(message);
		}
	}
	
	public boolean validateCredetails(String userName , String password) {
		
		User user = userRepository.findByUserName(userName);
		if(user.getPassword().equals(passwordEncoder.encode(password))){
			return true;
		} 
		return false;
	}

}
