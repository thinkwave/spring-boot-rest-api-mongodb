package com.exam.api.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
    

    @KafkaListener(topics="mongo1.movie_db.movie_collection", groupId = "cdc-grp-2")
    public void consume(@Headers MessageHeaders headers, @Payload String payload, Acknowledgment acknowledgment) { 
        log.info("\n===== cdc-grp-2 : {} =====\n CONSUME HEADERS : {}\n", Thread.currentThread().getId(), headers.toString());
        log.info("CONSUME PAYLOAD : " + payload); 
        acknowledgment.acknowledge();
    }

    // public void consume(String message) throws IOException {
        
    //     log.info("Kafka로 받은 메세지 : " + message);

    // }


}
