package com.example.kidsland.backend;

public class User {

    //Variaveis de instancia

    private int id_user;
    private String email;
    private String password;
    private String login_type;
    private String status;

    //Construtores

    public User() {
    }

    public User(int id_user, String email, String password, String login_type, String status) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.login_type = login_type;
        this.status = status;
    }

    //Seletores e modificadores
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
