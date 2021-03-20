package com.example.kidsland;

public class ListItem {

    private String tittle;
    private String location;

    public ListItem(String tittle, String location) {
        this.tittle = tittle;
        this.location = location;
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
}
