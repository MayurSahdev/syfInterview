package com.syf.interview.imageresource.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.syf.interview.imageresource.common.ImageResourceConstants;

@Component
public class ImageResourceWebClient {
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	public void updateImageDetails(String title , String description , String imageName) {

		MultiValueMap<String, String> imageDetails = new LinkedMultiValueMap<>();
		imageDetails.add("title", title);
		imageDetails.add("description", description);
		
		Object result = webClientBuilder.build().post().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header("Authorization", "Bearer 833072275b84a0ccddb48ea653ab99aaccf8387d")
				.body(BodyInserters.fromFormData(imageDetails)).retrieve().bodyToMono(Object.class).block();

		System.out.println(result);

	}
	
	public void deleteImage(String imageName) {

		Object result = webClientBuilder.build().delete().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header("Authorization", "Bearer 833072275b84a0ccddb48ea653ab99aaccf8387d")
				.retrieve().bodyToMono(Object.class).block();

		System.out.println(result);

	}
	
	public void getImage(String imageName) {

		Object result = webClientBuilder.build().get().uri(ImageResourceConstants.IMGUR_API_URL + "/" + imageName)
				.header("Authorization", "Bearer 833072275b84a0ccddb48ea653ab99aaccf8387d")
				.retrieve().bodyToMono(Object.class).block();

		System.out.println(result);

	}
	
	public void uploadImage(MultipartFile file) {
		Object result = webClientBuilder.build()
				.post().uri(ImageResourceConstants.IMGUR_API_URL)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.header("Authorization", "Bearer 833072275b84a0ccddb48ea653ab99aaccf8387d")
				.body(BodyInserters.fromMultipartData("image", new FileSystemResource("D:\\ImageOri.jpg")))
				.retrieve()
				.bodyToMono(Object.class).block();
		
		System.out.println(result);
	}

}
