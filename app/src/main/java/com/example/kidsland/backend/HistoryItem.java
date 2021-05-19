package com.example.kidsland.backend;

public class HistoryItem {

    private String tittle;
    private String location;
    private String photo;
    private String date;
    private int points, points_evaluation;
    private int id_item;

    public HistoryItem(String tittle, String location, String photo, String date, int points, int points_evaluation, int id_item) {
        this.tittle = tittle;
        this.location = location;
        this.photo = photo;
        this.date = date;
        this.points = points;
        this.points_evaluation = points_evaluation;
        this.id_item = id_item;
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

    public int getPoints_evaluation() {
        return points_evaluation;
    }

    public void setPoints_evaluation(int points_evaluation) {
        this.points_evaluation = points_evaluation;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }
}
