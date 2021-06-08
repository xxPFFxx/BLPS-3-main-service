package com.example.uploadingfiles.services;


import com.example.uploadingfiles.POJO.DeliveryMessageInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    @Autowired
    KafkaTemplate<String, DeliveryMessageInformation> kafkaTemplate;

    public void send(DeliveryMessageInformation info){
        kafkaTemplate.send("Notification_Topic", info);
    }
}
