package com.example.kidsland.backend;

public class Tutor extends User {
    private int id_tutor;
    private String name;
    private String birth_date;
    private String address;
    private String county;
    private String post_code;
    private int phone_number;

public Tutor (){

}
        public Tutor(int id_tutor, String name, String birth_date, String address, String county, String post_code, int phone_number) {
        this.id_tutor = id_tutor;
        this.name = name;
        this.birth_date = birth_date;
        this.address = address;
        this.post_code = post_code;
        this.phone_number = phone_number;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) { this.county = county; }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) { this.post_code = post_code; }

    public int getPhone_number() { return phone_number; }

    public void setPhone_number(int phone_number) { this.phone_number = phone_number; }


    @Override
    public String toString() {
        return "Tutor{" + "id_tutor=" + id_tutor + ", name=" + name + ", birth_date=" + birth_date + ", county=" + county + ", post_code=" + post_code + ", phone_number=" + phone_number + '}'; }

}
