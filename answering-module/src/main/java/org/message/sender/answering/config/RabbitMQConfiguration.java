package org.message.sender.answering.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableRabbit
public class RabbitMQConfiguration
{
    @Value("${rabbitmq.username:rabbitmq}")
    private String username;
    @Value("${rabbitmq.password:rabbitmq}")
    private String password;
    @Value("${rabbitmq.host:localhost}")
    private String host;
    @Value("${rabbitmq.virtualHost}")
    private String virtualHost;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.messageRoutingKey}")
    private String messageRoutingKey;
    private String maileRoutingKey;
    private String statusRoutingKey;
    @Value("${rabbitmq.messageQueue}")
    private String messageQueue;
    private String mailQueue;
    private String messageStatusQueue;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPort(5672);
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(messageQueue);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(messageRoutingKey);
    }

}
