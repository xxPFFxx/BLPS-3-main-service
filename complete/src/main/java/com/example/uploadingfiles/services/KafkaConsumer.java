package com.example.uploadingfiles.services;

import com.example.uploadingfiles.POJO.DeliveryMessageInformation;
import com.example.uploadingfiles.POJO.NotificationToMainMessage;
import com.example.uploadingfiles.exceptions.VideoInfoNotFoundException;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.services.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class KafkaConsumer {
    @Autowired
    private VideoInfoService videoInfoService;

    @Transactional
    @KafkaListener(topics = "NotificationTopich", groupId = "NotificationToMain", containerFactory = "kafkaListenerContainerFactory")
    public void consume(NotificationToMainMessage message){
        System.out.println("Consumed message: " + message.getSubscribers());
        System.out.println("Consumed link: " + message.getLink());
        videoInfoService.updateNotificationsSent(message.getLink(), message.getSubscribers().size());
        }

    }
