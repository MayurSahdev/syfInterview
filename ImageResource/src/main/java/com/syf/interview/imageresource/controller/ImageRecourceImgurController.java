package com.syf.interview.imageresource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.syf.interview.imageresource.model.ImageMetaData;
import com.syf.interview.imageresource.service.UserService;

@RestController
public class ImageRecourceImgurController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRecourceImgurController.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "/image")
	public String viewImages(@RequestParam String imageName) {
		String imageUrl = userService.getImage(imageName);
		return "Image can be seen at : " + imageUrl;
	}
	

	@PostMapping(value = "/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadImage(@RequestPart("image") MultipartFile file) {
		userService.uploadImageService(file);
		return "Image is created Successfully.";
	}

	@PatchMapping(value = "/image")
	public String updateImage(@RequestBody ImageMetaData imageDetails) {
		userService.updateImageTitle(imageDetails);
		return "Details for the Image : " + imageDetails.getImageName() + " have been updated.";
	}

	@DeleteMapping(value = "/image")
	public String deleteImageByName(@RequestParam String imageName) {
		userService.deleteImageService(imageName);
		return "Image  " + imageName + " is deleted.";
	}

}
