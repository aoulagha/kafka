package com.amin.dev.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.TopicBuilder;

import static com.amin.dev.kafka.config.ConfigConstants.kafkaTopic;


public class KafkaTopicConfig {

    @Bean
    public NewTopic aouKafkaTopic() {

        return TopicBuilder.name(
                kafkaTopic).build();
    }
}
