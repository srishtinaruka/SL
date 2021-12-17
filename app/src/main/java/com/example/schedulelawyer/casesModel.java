package com.example.schedulelawyer;

public class casesModel {
    String name;
    String number;
    String itemId;

    public casesModel() {
    }

    public casesModel(String name, String number, String itemId) {
        this.name = name;
        this.number = number;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getItemId() {
        return itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
