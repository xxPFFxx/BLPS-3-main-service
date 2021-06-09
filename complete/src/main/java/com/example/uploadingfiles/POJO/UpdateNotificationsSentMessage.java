package com.example.uploadingfiles.POJO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class UpdateNotificationsSentMessage implements Serializable {
    public UpdateNotificationsSentMessage() {
    }
    private String link;

    private ArrayList<String> subscribers;

    public UpdateNotificationsSentMessage(String link, ArrayList<String> subscribers) {
        this.link = link;
        this.subscribers = subscribers;
    }

    public ArrayList<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(ArrayList<String> subscribers) {
        this.subscribers = subscribers;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
