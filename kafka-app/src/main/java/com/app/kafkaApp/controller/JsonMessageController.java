package com.app.kafkaApp.controller;

import com.app.kafkaApp.Payload.User;
import com.app.kafkaApp.services.kafka.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
    private JsonKafkaProducer producer;
    JsonMessageController(JsonKafkaProducer producer){
        this.producer = producer;
    }
    @PostMapping("/publish")
    public ResponseEntity GetJsonMessage(@RequestBody User user)
    {
        producer.sendMessage(user);
        return ResponseEntity.ok("JSON message received from producer" );
    }
}


