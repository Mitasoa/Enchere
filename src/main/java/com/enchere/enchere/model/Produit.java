package com.enchere.enchere.model;

import java.sql.Date;
import java.time.LocalTime;

import com.mongodb.internal.connection.Time;

public class Produit {
    // id | nom | prix | utilisateurid | categorieid | dateencheriser | duree | etat
    int id;
    String nom;
    int utilisateurid;
    int categorieid;
    Date dateencheriser;
    LocalTime duree;
    int etat;

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public void setDateencheriser(Date dateencheriser) {
        this.dateencheriser = dateencheriser;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUtilisateurid(int utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public Date getDateencheriser() {
        return dateencheriser;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public int getEtat() {
        return etat;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getUtilisateurid() {
        return utilisateurid;
    }

}
