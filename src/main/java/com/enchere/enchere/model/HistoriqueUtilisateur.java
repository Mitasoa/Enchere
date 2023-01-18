package com.enchere.enchere.model;

import java.sql.Time;
import java.sql.Timestamp;

public class HistoriqueUtilisateur {
    private int id;
    public String nom;
    public float prix;
    public Timestamp dateencheriser;
    public Time duree;
    String categorie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Timestamp getDateencheriser() {
        return dateencheriser;
    }

    public void setDateencheriser(Timestamp dateencheriser) {
        this.dateencheriser = dateencheriser;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
