package com.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangeProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    WikimediaChangeProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recent_change";
        //listen event to wikimedia
        BackgroundEventHandler eventHandler = new WikimediaEventChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder bgBuilder = new BackgroundEventSource.Builder(eventHandler, builder);
        BackgroundEventSource eventSource = bgBuilder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(2);
    }
}
