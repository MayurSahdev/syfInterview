package com.syf.interview.imageresource.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.syf.interview.imageresource.entity.ImageDetail;

@Service
public class KafkaConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics="userDetails", groupId = "syfGroup")
	public void consume(List<ImageDetail> message) {
		LOGGER.info(String.format("Message received -> %s", message.toString()));
	}

}
