package com.example.challenge.config;

import com.hazelcast.core.HazelcastInstance;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class HazelcastTestConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Mockito.mock(HazelcastInstance.class);
    }
}
