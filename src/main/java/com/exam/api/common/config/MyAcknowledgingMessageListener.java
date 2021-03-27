package com.exam.api.common.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener; 
import org.springframework.kafka.listener.AcknowledgingMessageListener; 
import org.springframework.kafka.support.Acknowledgment; 
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j; 


@Slf4j
@Component 
public class MyAcknowledgingMessageListener implements AcknowledgingMessageListener<String, String> { 
    

    @Override 
    @KafkaListener(topics = "mongo1.movie_db.movie_collection", groupId = "cdc-grp-1", containerFactory = "kafkaListenerContainerFactory") 
    public void onMessage(ConsumerRecord data, Acknowledgment acknowledgment) { 
        try { 
            log.info("\n===== cdc-grp-1 : {} ===== \nconsume data: {}\n", Thread.currentThread().getId(), data.toString()); 
            acknowledgment.acknowledge(); 
        } catch (Exception e) { 
            log.error("consume cause exception : " + e); 
        } 
    } 

}
