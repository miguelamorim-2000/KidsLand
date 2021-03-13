package com.example.kidsland.backend;

public class Reward {
    private int id_reward;
    private String name;
    private String photo;
    private int points;

    //Construtor

    public Reward(int id_reward, String name, String photo, int points) {
        this.id_reward = id_reward;
        this.name = name;
        this.photo = photo;
        this.points = points;
    }


    //Getter

    public int getId_reward() {
        return id_reward;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getPoints() {
        return points;
    }


    //Setter


    public void setId_reward(int id_reward) {
        this.id_reward = id_reward;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
