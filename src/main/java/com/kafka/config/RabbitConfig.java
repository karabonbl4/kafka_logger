package com.kafka.config;

import com.kafka.service.RabbitConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitProperties rabbitProperties;

    @Bean
    public Queue queue(){
        return new Queue(rabbitProperties.getQueue());
    }

    @Bean
    public Queue exceptionQueue(){
        return new Queue(rabbitProperties.getExceptionQueue());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setMessageConverter(rabbitMessageConverter());
        return containerFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter rabbitMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MessageListenerAdapter rabbitListenerAdapter(RabbitConsumer listener){
        return new MessageListenerAdapter(listener);
    }
}