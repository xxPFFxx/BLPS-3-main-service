package com.example.uploadingfiles.util;

import com.example.uploadingfiles.POJO.NotifyAuthorMessage;
import com.example.uploadingfiles.POJO.NotifySubscribersMessage;
import com.example.uploadingfiles.services.VideoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class ScheduledTasks {
    private final VideoInfoService videoInfoService;
    private final KafkaTemplate<String, NotifyAuthorMessage> kafkaTemplate;
    private static final String TOPIC = "NotifyAuthorTopicFromMain";

    public ScheduledTasks(VideoInfoService videoInfoService, KafkaTemplate<String, NotifyAuthorMessage> kafkaTemplate) {
        this.videoInfoService = videoInfoService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        ArrayList<String> links = videoInfoService.setPopularStatus();
        if (links.size()>0){
            kafkaTemplate.send(TOPIC, new NotifyAuthorMessage(links));
        }
    }
}
