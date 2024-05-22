package com.producer;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaEventChangesHandler implements BackgroundEventHandler {
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaEventChangesHandler.class);
    public WikimediaEventChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data --> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}