package com.kafka.service;

import com.kafka.config.KafkaProperties;
import com.kafka.model.LogMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaProperties kafkaProperties;

    private final KafkaTemplate<String, LogMessage> kafkaTemplate;

    public void sendMessage(LogMessage message){
        kafkaTemplate.send(kafkaProperties.getTopic(), message);
    }

    public void sendException(LogMessage message){
        kafkaTemplate.send(kafkaProperties.getExceptionTopic(), message);
    }
}
