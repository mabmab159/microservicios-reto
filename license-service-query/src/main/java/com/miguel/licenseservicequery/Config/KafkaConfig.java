package com.miguel.licenseservicequery.Config;

import com.miguel.licenseservicequery.Model.Audit;
import com.miguel.licenseservicequery.Model.License;
import com.miguel.licenseservicequery.Repositories.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final LicenseRepository licenseRepository;

    @Value("${kafka.miguel.server}")
    private String kafkaServer;
    @Value("${kafka.miguel.port}")
    private String kafkaPort;
    @Value("${kafka.miguel.topic-license}")
    private String topicName;

    @Bean
    public ConsumerFactory<String, License> consumerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        kafkaProperties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.miguel.licenseservicequery.Config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.miguel.licenseservicequery.Model.License");
        kafkaProperties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        kafkaProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.miguel.*");
        return new DefaultKafkaConsumerFactory<>(kafkaProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, License> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, License> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "miguel-topic-license")
    public void listenTopic(License obj) {
        licenseRepository.save(obj).subscribe();
    }

    public ProducerFactory<String, Audit> producerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaProperties);
    }

    @Bean
    public KafkaTemplate<String, Audit> kafkaTemplateAudit() {
        return new KafkaTemplate<>(producerFactory());
    }
}
