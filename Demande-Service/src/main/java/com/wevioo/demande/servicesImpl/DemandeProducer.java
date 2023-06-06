package com.wevioo.demande.servicesImpl;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.ParametrageEvent;

@Service
public class DemandeProducer {

	/**
	 * Service class responsible for producing messages to a Kafka topic.
	 * The class is annotated with @Service to indicate it as a Spring service component.
	 */

	// Private fields
	private NewTopic topic; // Holds the Kafka topic
	private KafkaTemplate<String, ParametrageEvent> kafkaTemplate; // KafkaTemplate for sending messages

	/**
	 * Constructor for DemandeProducer.
	 * 
	 * @param topic         The Kafka topic to send messages to.
	 * @param kafkaTemplate The KafkaTemplate used for sending messages.
	 */
	public DemandeProducer(NewTopic topic, KafkaTemplate<String, ParametrageEvent> kafkaTemplate) {
	    super();
	    this.topic = topic;
	    this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * Sends a message to the Kafka topic.
	 * 
	 * @param event The ParametrageEvent to be sent as the payload of the message.
	 */
	public void sendMessage(ParametrageEvent event) {
	    // Build a message with the ParametrageEvent payload
	    Message<ParametrageEvent> message = MessageBuilder.withPayload(event)
	        .setHeader(KafkaHeaders.TOPIC, "topic1")
	        .build();
	        
	    // Send the message to the Kafka topic using the KafkaTemplate
	    kafkaTemplate.send(message);
	}

}
