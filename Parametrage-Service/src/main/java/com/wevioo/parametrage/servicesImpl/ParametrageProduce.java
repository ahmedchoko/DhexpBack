package com.wevioo.parametrage.servicesimpl;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.ParametrageEvent;

/**
 * Service implementation for producing Kafka messages related to parameterization.
 */
@Service
public class ParametrageProduce {

    private NewTopic topic;
    private KafkaTemplate<String, ParametrageEvent> kafkaTemplate;

    public ParametrageProduce(NewTopic topic, KafkaTemplate<String, ParametrageEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a ParametrageEvent message to Kafka.
     *
     * @param event The ParametrageEvent to be sent.
     */
    public void sendMessage(ParametrageEvent event) {
        Message<ParametrageEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "topic2")
                .build();
        kafkaTemplate.send(message);
    }
}
