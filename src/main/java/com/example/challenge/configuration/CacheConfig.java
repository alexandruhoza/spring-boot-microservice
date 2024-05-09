package com.example.challenge.configuration;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    @Profile("!test")
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config("hazelcast-instance");
        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);
        evictionConfig.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE);
        evictionConfig.setSize(100);
        config.addMapConfig(new MapConfig().setName("parents").setEvictionConfig(evictionConfig).setTimeToLiveSeconds(200));
        return Hazelcast.newHazelcastInstance(config);
    }
}


