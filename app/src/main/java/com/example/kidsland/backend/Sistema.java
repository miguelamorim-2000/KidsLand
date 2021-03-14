package com.example.kidsland.backend;

public class Sistema {
    private User userConnected;

    public Sistema() {
    }


    //Getter

    public User getUserConnected() {
        return userConnected;
    }

    //Setter
    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }

    //Verify if User exists

    public void authenticateUtilizador(String username, String password)  {

    }
}


