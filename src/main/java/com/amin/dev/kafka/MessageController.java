package com.amin.dev.kafka;

import com.amin.dev.kafka.config.ConfigConstants;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String,
            MovieRequest> KafkaTemplate;

    public MessageController(KafkaTemplate<String, MovieRequest> kafkaTemplate) {
        KafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void publish(@RequestBody MovieRequest movie) {
        KafkaTemplate.send(ConfigConstants.kafkaTopic, movie);

    }
}
