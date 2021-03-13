package com.example.kidsland.backend;

public class RequestActivity {

    private int id_request;
    private int id_entity;
    private String date;
    private String time;
    private String type;
    private String address;
    private String county;
    private String district;
    private String state;
    private String post_code;
    private String description;

    public RequestActivity() {
    }

    public RequestActivity(int id_request, int id_entity, String date, String time, String type, String address, String county, String district, String state, String post_code, String description) {
        this.id_request = id_request;
        this.id_entity = id_entity;
        this.date = date;
        this.time = time;
        this.type = type;
        this.address = address;
        this.county = county;
        this.district = district;
        this.state = state;
        this.post_code = post_code;
        this.description = description;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public int getId_entity() {
        return id_entity;
    }

    public void setId_entity(int id_entity) {
        this.id_entity = id_entity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Request_Activity{" + "id_request=" + id_request + ", id_entity=" + id_entity + ", date=" + date + ", time=" + time + ", type=" + type + ", address=" + address + ", county=" + county + ", district=" + district + ", state=" + state + ", post_code=" + post_code + ", description=" + description + '}';
    }



}
