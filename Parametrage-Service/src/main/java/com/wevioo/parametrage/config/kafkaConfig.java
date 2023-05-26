package com.wevioo.parametrage.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {

	


	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic2").build();
	}
}