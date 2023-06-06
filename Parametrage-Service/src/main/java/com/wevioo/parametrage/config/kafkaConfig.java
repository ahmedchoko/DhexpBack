package com.wevioo.parametrage.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {

	

	 /**
     * Creates a new Kafka topic.
     *
     * @return The NewTopic object representing the Kafka topic
     */
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic2").build();
	}
}
