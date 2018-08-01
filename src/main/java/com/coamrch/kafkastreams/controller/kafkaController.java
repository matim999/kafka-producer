package com.coamrch.kafkastreams.controller;

import com.coamrch.kafkastreams.model.Car;
import com.coamrch.kafkastreams.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class kafkaController {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "test";
    private static final String TOPIC2 = "test2";

    @Autowired
    public kafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(path = "post/user")
    public String sendUser(@RequestBody User user) {
        ListenableFuture<SendResult<String, String>> producerRecord = null;
        try {
            producerRecord = kafkaTemplate.send(TOPIC, mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            System.out.println(producerRecord.get().getProducerRecord().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Message Send";
    }

    @PostMapping(path = "post/car")
    public String sendCar(@RequestBody Car car) {
        ListenableFuture<SendResult<String, String>> producerRecord = null;
        try {
            producerRecord = kafkaTemplate.send(TOPIC2, mapper.writeValueAsString(car));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            System.out.println(producerRecord.get().getProducerRecord().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Message Send";
    }

//    @PostMapping(path = "post2")
//    public String sendUser(@RequestBody User user) {
//        userKafkaTemplate.send(TOPIC2, user);
//        return "Message send";
//    }
}
