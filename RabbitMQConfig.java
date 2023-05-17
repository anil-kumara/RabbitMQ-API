package spring.boot.RabbitMQ.SpringBootRabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //giving dynamic value in application.properties to avoid hardcode @Value
    @Value("${rabbitmq.queue.name}")
    private String queue;


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //queue for json msg store
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    //routingKey for json msg store
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    //spring bean for RabbitMQ queue
    @Bean
    public Queue queue() {
        return new Queue(queue);//we can pass value here it will lead hard coding
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //to store json messages
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    //binding between the queue and the exchange using routing key
    public Binding bind() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    //binding between the jsonQueue and the exchange using routing key
    @Bean
    public Binding jsonBind() {
        return BindingBuilder.bind(jsonQueue()).
                to(exchange()).
                with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //connection factory
    //RabbitTemplate
    //RabbitAdmin these all are autoconfigure by SpringBoot
}
