package com.enchere.enchere.model;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.enchere.enchere.repository.MobileRepository;
import com.mongodb.internal.connection.Time;

public class Produit {
    @Autowired
    MobileRepository mobileRep = new MobileRepository();

    int id;
    String nom;
    int utilisateurid;
    int categorieid;
    String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    LocalDateTime dateencheriser;
    LocalTime duree;
    int etat;
    double prix;
    String status;

    ArrayList<Photo> photo1;

    public ArrayList<Photo> getPhoto1() {
        return this.photo1;
    }

    public void setPhoto1(ArrayList<Photo> photo1) {
        this.photo1 = photo1;
    }

    public void setPhoto1(MobileRepository mobileRep) {
        this.photo1 = mobileRep.getPhoto(this.getId());
    }

    Photo[] photo;

    public Photo[] getPhoto() {
        return photo;
    }

    public void setPhoto(Photo[] photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
