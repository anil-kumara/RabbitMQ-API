package spring.boot.RabbitMQ.SpringBootRabbitMq.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import spring.boot.RabbitMQ.SpringBootRabbitMq.dto.User;
import spring.boot.RabbitMQ.SpringBootRabbitMq.publisher.RabbitMQProducer;

//logic for consuming json msg
@Service
public class RabbitMqJsonConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Json message received -> %s",user.toString()));
    }
}
