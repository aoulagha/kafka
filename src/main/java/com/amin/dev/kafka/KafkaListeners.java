package com.amin.dev.kafka;

import com.amin.dev.kafka.config.ConfigConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics =
            ConfigConstants.kafkaTopic,
            groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener " +
                "Received : " + data
        );
    }
}
