package spring.boot.RabbitMQ.SpringBootRabbitMq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.RabbitMQ.SpringBootRabbitMq.dto.User;

@Service
public class RabbitMQJsonProducer {

    @Value("${my-json-queue}")
    private String exchange;
    @Value("routing-key-for-json-queue")
    private String routingJsonKey;

    //constructor injection
    private RabbitTemplate rabbitTemplate;

    //send json msg to queue we use rabbitTemplate constructor injection
    @Autowired
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //creating Logger object to log the message
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
    public void sendJsonMessage(User user ){
    LOGGER.info(String.format("Json message sent -> %s",user.toString()));
    rabbitTemplate.convertAndSend(exchange,routingJsonKey,user);
    }
}