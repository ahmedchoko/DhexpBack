package com.wevioo.parametrage.servicesImpl;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.ParametrageEvent;


@Service
public class ParametrageProduce {
	private static final Logger LOGGER =LoggerFactory.getLogger(ParametrageProduce.class) ;

	private NewTopic topic;
	private KafkaTemplate<String,ParametrageEvent> kafkaTemplate ;
	public ParametrageProduce(NewTopic topic, KafkaTemplate<String, ParametrageEvent> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	} 
	
	public void sendMessage(ParametrageEvent event) {
		LOGGER.info(String.format("parametrage event "));
		Message <ParametrageEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, "topic2").build();
		kafkaTemplate.send(message);
	}
	
}
