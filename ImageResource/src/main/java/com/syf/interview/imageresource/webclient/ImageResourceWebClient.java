package com.syf.interview.imageresource.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.syf.interview.imageresource.common.ImageResourceConstants;
import com.syf.interview.imageresource.model.ResponseData;

@Component
public class ImageResourceWebClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageResourceWebClient.class);

	@Value("${spring.imgur.apikey}")
	private String imgurApiKey;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	/**
	 * Method to call Imgur API and update the title and Description of Image.
	 * @param title
	 * @param description
	 * @param imageName
	 */
	public void updateImageDetails(String title , String description , String imageName) {
		// TODO : Add Exception handling if update failed.

		MultiValueMap<String, String> imageDetails = new LinkedMultiValueMap<>();
		imageDetails.add(ImageResourceConstants.TITLE, title);
		imageDetails.add(ImageResourceConstants.DESC, description);
		
		webClientBuilder.build().post().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header(ImageResourceConstants.AUTHORIZATION, imgurApiKey)
				.body(BodyInserters.fromFormData(imageDetails)).retrieve().bodyToMono(Object.class).block();
		
		LOGGER.debug("Image has been updated");

	}
	
	/**
	 * Method to call Imgur API and delete the image
	 * @param imageName
	 */
	public void deleteImage(String imageName) {
		// TODO : Add Exception handling if Image is not found or already deleted.

		webClientBuilder.build().delete().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header(ImageResourceConstants.AUTHORIZATION, imgurApiKey)
				.retrieve().bodyToMono(Object.class).block();
		
		LOGGER.debug("Image has been deleted");
	}
	
	/**
	 * Method to call Imgur API and get the image with image name.
	 * @param imageName
	 * @return
	 */
	public ResponseData getImage(String imageName) {
		// TODO : Add Exception handling if image is not found with imageName.

		return webClientBuilder.build().get().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header(ImageResourceConstants.AUTHORIZATION, imgurApiKey)
				.retrieve().bodyToMono(ResponseData.class).block();
		

	}
	
	/**
	 * Method to call Imgur API and create the new image.
	 * @param file
	 * @return
	 */
	public ResponseData uploadImage(MultipartFile file) {
		// TODO : Add Exception handling for failure to upload.
		return webClientBuilder.build()
				.post().uri(ImageResourceConstants.IMGUR_API_URL)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.header(ImageResourceConstants.AUTHORIZATION, imgurApiKey)
				.body(BodyInserters.fromMultipartData("image", new FileSystemResource("D:\\ImageOri.jpg")))
				.retrieve()
				.bodyToMono(ResponseData.class).block();

	}

}
