package edu.ucan.stock.producer;

import edu.ucan.stock.dto.LogModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducer {

    @Value("${kafka.topic.stock-log}")
    private String stockLogTopic;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendLog(LogModel logDTO) {
        String message = modelMapper.map(logDTO, String.class);
        kafkaTemplate.send(stockLogTopic, message);
        return "Log enviado com sucesso!";
    }
}
