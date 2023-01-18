package com.enchere.enchere.model;

public class Admin {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    private String motDePasse;

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /*
     * public Admin Login(String email, String pass) {
     * Admin admin=new Admin();
     * admin.setMotDePasse(pass);
     * admin.set
     * }
     */

}
