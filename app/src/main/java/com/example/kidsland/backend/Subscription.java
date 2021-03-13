package com.example.kidsland.backend;

public class Subscription {
    private int id_activities;
    private int id_child;
    private String evaluation;
    private int points;
    private String photo_1;
    private String photo_2;
    private String state;

    //Constructor

    public Subscription(int id_activities, int id_child, String evaluation, int points, String photo_1, String photo_2, String state) {
        this.id_activities = id_activities;
        this.id_child = id_child;
        this.evaluation = evaluation;
        this.points = points;
        this.photo_1 = photo_1;
        this.photo_2 = photo_2;
        this.state = state;
    }

    //Getter

    public int getId_activities() {
        return id_activities;
    }

    public int getId_child() {
        return id_child;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public int getPoints() {
        return points;
    }

    public String getPhoto_1() {
        return photo_1;
    }

    public String getPhoto_2() {
        return photo_2;
    }

    public String getState() {
        return state;
    }

    //Setter

    public void setId_activities(int id_activities) {
        this.id_activities = id_activities;
    }

    public void setId_child(int id_child) {
        this.id_child = id_child;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPhoto_1(String photo_1) {
        this.photo_1 = photo_1;
    }

    public void setPhoto_2(String photo_2) {
        this.photo_2 = photo_2;
    }

    public void setState(String state) {
        this.state = state;
    }
}
