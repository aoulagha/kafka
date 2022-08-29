package com.amin.dev.kafka.config;

import com.amin.dev.kafka.MovieRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka" +
            ".bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props =
                new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return props;
    }

    @Bean
    public ConsumerFactory<String,
            MovieRequest> consumerFactory() {
        JsonDeserializer<MovieRequest> movieRequestJsonDeserializer = new JsonDeserializer<>();
        movieRequestJsonDeserializer.addTrustedPackages("com.amin.dev.kafka");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                movieRequestJsonDeserializer);
    }

    /**
     * Listener Kafka Consumer
     * @param consumerFactory
     * Dependency injection param
     * @return The listener container
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MovieRequest>>
        factory(ConsumerFactory<String, MovieRequest> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, MovieRequest> factory
                =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
