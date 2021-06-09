package com.example.uploadingfiles.services;


import com.example.uploadingfiles.POJO.NotifySubscribersMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    @Autowired
    KafkaTemplate<String, NotifySubscribersMessage> kafkaTemplate;

    public void send(NotifySubscribersMessage info){
        kafkaTemplate.send("Notification_Topic", info);
    }
}
