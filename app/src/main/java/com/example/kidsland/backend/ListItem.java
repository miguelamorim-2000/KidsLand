package com.example.kidsland.backend;

public class ListItem {

    private String tittle;
    private String location;
    private String photo;

    public ListItem(String tittle, String location, String photo) {
        this.tittle = tittle;
        this.location = location;
        this.photo = photo;
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
}
