package com.miguel.clientservice.Utils;

import com.miguel.clientservice.Model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, Client> kafkaTemplate;
    @Value("${kafka.miguel.topic-client}")
    private String topicName;

    public void sendMessage(Client obj) {
        kafkaTemplate.send(topicName, obj);
    }
}
