package com.enchere.enchere.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Produit1 {
    int id;
    String nom;
    int utilisateurid;
    int categorieid;
    LocalDateTime dateencheriser;
    LocalTime duree;
    String produit;

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    int etat;
    double prix;

    Photo[] photo;

    public void setPhoto(Photo[] photo) {
        this.photo = photo;
    }

    public Photo[] getPhoto() {
        return photo;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public void setDateencheriser(LocalDateTime dateencheriser) {
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

    public LocalDateTime getDateencheriser() {
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
