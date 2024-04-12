package com.miguel.licenseservice.Utils;

import com.miguel.licenseservice.Model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, License> kafkaTemplate;
    @Value("${kafka.miguel.topic}")
    private String topicName;

    public void sendMessage(License obj) {
        kafkaTemplate.send(topicName, obj);
    }
}
