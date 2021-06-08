package com.example.uploadingfiles.POJO;

import java.io.Serializable;

public class DeliveryMessageInformation implements Serializable {
    private String link;

    public DeliveryMessageInformation() {
    }

    public DeliveryMessageInformation(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
