package com.example.kidsland.backend;

public class Child {

    String name,birth_date, photo;
    int id_child,total_points;

    public Child(String name, int total_points, String birth_date, int id_child, String photo) {
        this.name = name;
        this.total_points = total_points;
        this.birth_date = birth_date;
        this.id_child = id_child;
        this.photo= photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_child() {
        return id_child;
    }

    public void setId_child(int id_child) {
        this.id_child = id_child;
    }
}
