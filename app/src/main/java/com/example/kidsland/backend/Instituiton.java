package com.example.kidsland.backend;

public class Instituiton extends User {
    private int id_entity;
    private String name;
    private int nif;
    private String type;
    private String address;
    private String district;
    private String county;
    private String post_code;
    private int phone_number;

    public Instituiton (){

    }

    public Instituiton(int id_entity, String name, int nif, String type, String address, String district, String county, String post_code, int phone_number) {
        this.id_entity = id_entity;
        this.name = name;
        this.nif = nif;
        this.type = type;
        this.address = address;
        this.district = district;
        this.county = county;
        this.post_code = post_code;
        this.phone_number = phone_number;

    }

    public int getId_entity() {
        return id_entity;
    }

    public void setId_entity(int id_entity) {
        this.id_entity = id_entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) { this.district = district; }

    public String getCounty() { return county; }

    public void setCounty(String county) { this.county = county; }

    public String getPost_code() { return post_code; }

    public void setPost_code(String post_code) { this.post_code = post_code; }

    public int getPhone_number() { return phone_number; }

    public void setPhone_number(int phone_number) { this.phone_number = phone_number; }


    @Override
    public String toString() {
        return "Instituiton{" + "id_entity=" + id_entity + ", name=" + name + ", nif=" + nif + ", type=" + type + ", address=" + address + ", district=" + district + ", county=" + county + ", post_code=" + post_code + ", phone_number=" + phone_number +'}'; }


}
