package com.example.kidsland.backend;

public class ListItem  {

    private String tittle;
    private String location;
    private String photo;
    private int id_item;
    private String time, date;


    public ListItem(String tittle, String location, String photo, int id_item, String time, String date) {
        this.tittle = tittle;
        this.location = location;
        this.photo = photo;
        this.id_item = id_item;
        this.time = time;
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


