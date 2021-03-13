package com.example.kidsland.backend;

public class Activity {
        private int id_activity;
        private int id_reward;
        private String status;

//Constructor
    public Activity(int id_activity, int id_reward, String status) {
        this.id_activity = id_activity;
        this.id_reward = id_reward;
        this.status = status;
    }

    //Getter

    public int getId_activity() {
        return id_activity;
    }

    public int getId_reward() {
        return id_reward;
    }

    public String getStatus() {
        return status;
    }

    //Setter

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public void setId_reward(int id_reward) {
        this.id_reward = id_reward;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
