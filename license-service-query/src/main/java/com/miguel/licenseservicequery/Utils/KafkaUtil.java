package com.miguel.licenseservicequery.Utils;

import com.miguel.licenseservicequery.Model.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, Audit> kafkaTemplate;

    @Value("${kafka.miguel.topic-audit}")
    private String topicName2;

    public void sendMessage(Audit obj) {
        kafkaTemplate.send(topicName2, obj);
    }

}
