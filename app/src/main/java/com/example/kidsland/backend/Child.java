package com.example.kidsland.backend;

public class Child {


    private int id_child;
    private int id_tutor;
    private String name;
    private int total_points;
    private String birth_date;
    private String photo;

    public Child() {
    }

    public Child(int id_child, int id_tutor, String name, int total_points, String birth_date, String photo) {
        this.id_child = id_child;
        this.id_tutor = id_tutor;
        this.name = name;
        this.total_points = total_points;
        this.birth_date = birth_date;
        this.photo = photo;
    }

    public int getId_child() {
        return id_child;
    }

    public void setId_child(int id_child) {
        this.id_child = id_child;
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
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

    @Override
    public String toString() {
        return "Child{" + "id_child=" + id_child + ", id_tutor=" + id_tutor + ", name=" + name + ", total_points=" + total_points + ", birth_date=" + birth_date + ", photo=" + photo + '}';
    }
}
