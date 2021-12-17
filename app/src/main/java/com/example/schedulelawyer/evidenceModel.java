package com.example.schedulelawyer;

public class evidenceModel {
    String name;
    String date;
    String itemId;

    public evidenceModel() {
    }

    public evidenceModel(String name, String date, String itemId) {
        this.name = name;
        this.date = date;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getItemId() {
        return itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
