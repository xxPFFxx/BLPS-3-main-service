package com.example.uploadingfiles.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class NotifyAuthorMessage implements Serializable {
    private ArrayList<String> links;

    public NotifyAuthorMessage() {
    }

    public NotifyAuthorMessage(ArrayList<String> links) {
        this.links = links;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }
}
