package spring.boot.RabbitMQ.SpringBootRabbitMq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.RabbitMQ.SpringBootRabbitMq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer producer;

    @Autowired
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    //rest api
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {//to get message form url we use @RequestParam
        producer.sendMessage(message);
        return ResponseEntity.ok("message sent to RabbitMQ...");
    }


}
