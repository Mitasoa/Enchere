package com.enchere.enchere.model;

public class Utilisateur {
    int id;
    String nom;
    String prenom;
    String mail;
    String motdepass;

    double solde;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotdepass(String motdepass) {
        this.motdepass = motdepass;
    }

    public String getMotdepass() {
        return motdepass;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Utilisateur Login(String mail, String password) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(mail);
        utilisateur.setMotdepass(password);
        
        return utilisateur;
    }

}
