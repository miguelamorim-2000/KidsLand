package com.example.kidsland.backend;

public class HistoryItem {

    private String tittle;
    private String location;
    private String photo;
    private String date;
    private int points;

    public HistoryItem(String tittle, String location, String photo, String date, int points) {
        this.tittle = tittle;
        this.location = location;
        this.photo = photo;
        this.date = date;
        this.points = points;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
