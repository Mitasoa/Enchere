package com.enchere.enchere.model;

public class Erreur {
    public Erreur(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
