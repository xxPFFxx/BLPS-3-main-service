package com.example.uploadingfiles.POJO;

import java.io.Serializable;

public class NotifySubscribersMessage implements Serializable {
    private String link;

    public NotifySubscribersMessage() {
    }

    public NotifySubscribersMessage(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
