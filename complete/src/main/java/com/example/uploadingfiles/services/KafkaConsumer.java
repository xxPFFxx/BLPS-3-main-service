package com.example.uploadingfiles.services;

import com.example.uploadingfiles.POJO.UpdateNotificationsSentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class KafkaConsumer {
    @Autowired
    private VideoInfoService videoInfoService;

    @Transactional
    @KafkaListener(topics = "NotificationTopich", groupId = "NotificationToMain", containerFactory = "kafkaListenerContainerFactory")
    public void consume(UpdateNotificationsSentMessage message){
        System.out.println("Consumed message: " + message.getSubscribers());
        System.out.println("Consumed link: " + message.getLink());
        videoInfoService.updateNotificationsSent(message.getLink(), message.getSubscribers().size());
        }

    }
