package com.example.kidsland.backend;

public class Collaborator extends User{

    //Variaveis de instancia

    private int id_collaborator;
    private String name;
    private int nif;
    private String type;
    private String address;
    private String county;
    private String district;
    private String post_code;
    private String email;
    private int phone_number;

    //Construtores


    public Collaborator() {
    }

    public Collaborator(int id_user, String email, String password, String login_type, String status, int id_collaborator, String name, int nif, String type, String address, String county, String district, String post_code, String email1, int phone_number) {
        super(id_user, email, password, login_type, status);
        this.id_collaborator = id_collaborator;
        this.name = name;
        this.nif = nif;
        this.type = type;
        this.address = address;
        this.county = county;
        this.district = district;
        this.post_code = post_code;
        this.email = email1;
        this.phone_number = phone_number;
    }

    //Seletores e modificadores

    public int getId_collaborator() {
        return id_collaborator;
    }

    public void setId_collaborator(int id_collaborator) {
        this.id_collaborator = id_collaborator;
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

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
