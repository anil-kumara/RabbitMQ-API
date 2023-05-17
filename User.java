package spring.boot.RabbitMQ.SpringBootRabbitMq.dto;

import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
}


/* --------> this class created to pass the value to the api in postman after running the application <---------- */
/* ex : "id" : 101
        "name" : "anil"
        "lastName" : "kumara"   */