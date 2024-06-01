package com.syf.interview.imageresource.controller;

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
import com.syf.interview.imageresource.entity.User;
import com.syf.interview.imageresource.model.ImageDetails;
import com.syf.interview.imageresource.service.UserService;

@RestController
public class ImageRecourceImgurController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/image")
	public String viewImages(@RequestParam String imageName) {
		String imagePath = userService.getImagePath();
		return "All the imanges are downloaded at path : " + imagePath;
	}

	@PostMapping(value = "/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadImage(@RequestPart("image") MultipartFile file) {
		return "Image is created Successfully.";
	}

	@PatchMapping(value = "/image")
	public String updateImage(@RequestBody ImageDetails imageDetails) {
		userService.updateImageTitle(imageDetails);
		return "Details for the Image : " + imageDetails.getImageName() + " have been updated.";
	}

	@DeleteMapping(value = "/image")
	public String deleteImageByName(@RequestParam String imageName) {
		userService.deleteImageService(imageName);
		return "Image  " + imageName + " is deleted.";
	}

}
