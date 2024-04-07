package com.miguel.cloudgateway.Config;
import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfig {
    @Bean
    public Capability configCapability(final MeterRegistry meterRegistry) {
        return new MicrometerCapability(meterRegistry);
    }
}