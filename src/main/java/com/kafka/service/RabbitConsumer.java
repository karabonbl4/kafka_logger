package com.kafka.service;

import com.kafka.model.LogMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitConsumer {

    private final KafkaProducer kafkaProducer;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(LogMessage message){
        log.debug(String.valueOf(message));
        kafkaProducer.sendMessage(message);
    }

    @RabbitListener(queues = "${rabbitmq.queue.exception}")
    public void receiveException(LogMessage message){
        log.debug(String.valueOf(message));
        kafkaProducer.sendException(message);
    }
}