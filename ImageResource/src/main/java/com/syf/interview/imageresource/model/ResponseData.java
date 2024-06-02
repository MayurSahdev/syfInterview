package com.syf.interview.imageresource.model;

import lombok.Data;

@Data
public class ResponseData {
	
	private String status;
	private String success;
	private ImageData data;

}
