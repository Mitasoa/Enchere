package com.enchere.enchere.model;

import java.time.LocalTime;

public class Categorie {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private LocalTime dureeMax;

    public LocalTime getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(LocalTime dureeMax) {
        this.dureeMax = dureeMax;
    }
}