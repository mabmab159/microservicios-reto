package com.miguel.auditservice.Config;

import com.miguel.auditservice.Model.Audit;
import com.miguel.auditservice.Repositories.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final AuditRepository auditRepository;
    @Value("${kafka.miguel.server}")
    private String kafkaServer;
    @Value("${kafka.miguel.port}")
    private String kafkaPort;
    @Value("${kafka.miguel.topic-audit}")
    private String topicName;

    @Bean
    public ConsumerFactory<String, Audit> consumerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        kafkaProperties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.miguel.auditservice.Config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.miguel.auditservice.Model.Audit");
        kafkaProperties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        kafkaProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.miguel.*");
        return new DefaultKafkaConsumerFactory<>(kafkaProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Audit> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Audit> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "miguel-topic-audit")
    public void listenTopic(Audit obj) {
        auditRepository.save(obj).subscribe();
    }
}
