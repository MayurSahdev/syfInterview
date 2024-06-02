package com.syf.interview.imageresource.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import com.syf.interview.imageresource.entity.ImageDetail;

@Service
public class KafkaProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	
	private KafkaTemplate<String , String> kafkaTemplate;
	
	public KafkaProducer(KafkaTemplate<String , String> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	public void sendMessage(Message<ImageDetail> message) {
		LOGGER.info(String.format("Message sent on Kafka Queue"));
		kafkaTemplate.send(message);
	}

}
