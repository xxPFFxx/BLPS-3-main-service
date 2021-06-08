package com.example.uploadingfiles.POJO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class NotificationToMainMessage implements Serializable {
    public NotificationToMainMessage() {
    }
    private String link;

    private ArrayList<String> subscribers;

    public NotificationToMainMessage(String link, ArrayList<String> subscribers) {
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
