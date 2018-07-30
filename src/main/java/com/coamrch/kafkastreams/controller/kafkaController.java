package com.coamrch.kafkastreams.controller;

import com.coamrch.kafkastreams.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kafkaController {
    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = "test";

    @PostMapping(path = "post")
    public String sendHello(@RequestBody User user) {

        kafkaTemplate.send(TOPIC, "1", user);

        return "Message send";
    }
}
