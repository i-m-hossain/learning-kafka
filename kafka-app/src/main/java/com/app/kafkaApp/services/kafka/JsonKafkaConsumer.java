package com.app.kafkaApp.services.kafka;

import com.app.kafkaApp.Payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "user-topic", groupId = "myGroup")
    public void consume(User user){
        LOGGER.info(String.format("Json message receive in kafka consumer ---> %s", user.toString()));
    }
}
