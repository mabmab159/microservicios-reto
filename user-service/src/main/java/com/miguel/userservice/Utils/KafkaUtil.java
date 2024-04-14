package com.miguel.userservice.Utils;

import com.miguel.userservice.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, User> kafkaTemplate;
    @Value("${kafka.miguel.topic}")
    private String topicName;

    public void sendMessage(User obj) {
        kafkaTemplate.send(topicName, obj);
    }
}
