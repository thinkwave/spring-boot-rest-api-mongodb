package com.exam.api.kafka;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
    

    // @KafkaListener(topics="mongo1.movie_db.movie_collection")
    // public void consume(@Headers MessageHeaders headers, @Payload String payload) { 
    //     log.info("CONSUME HEADERS : " + headers.toString());
    //     log.info("CONSUME PAYLOAD : " + payload); 
    // }
    // public void consume(String message) throws IOException {
        
    //     log.info("Kafka로 받은 메세지 : " + message);

    // }


}
