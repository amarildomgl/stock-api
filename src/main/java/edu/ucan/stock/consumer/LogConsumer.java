package edu.ucan.stock.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {

    @Value("${kafka.topic.stock-log}")
    private String stockLogTopic;

    @KafkaListener(topics = "stock-log", groupId = "stock-log-group")
    public void consume(String message) {
        System.out.println("Log do sistema: " + message);
    }
}
