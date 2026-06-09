package com.adi.activityservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public final static String EXCHANGE_NAME="activityExchange";
    public final static String QUEUE_NAME="activityQueue";
    public final static String ROUTING_KEY="activityKey";

    @Bean
    public Exchange getExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue getQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    public Binding getBinding(DirectExchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter getJsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }
}
