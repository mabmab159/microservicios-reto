package com.miguel.licenseservice.Utils;

import com.miguel.licenseservice.Model.Audit;
import com.miguel.licenseservice.Model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, License> kafkaTemplate;
    private final KafkaTemplate<String, Audit> kafkaTemplateAudit;
    @Value("${kafka.miguel.topic-license}")
    private String topicName;

    @Value("${kafka.miguel.topic-audit}")
    private String topicName2;

    public void sendMessage(License obj) {
        kafkaTemplate.send(topicName, obj);
    }

    public void sendMessageAudit(Audit obj) {
        kafkaTemplateAudit.send(topicName2, obj);
    }
}
