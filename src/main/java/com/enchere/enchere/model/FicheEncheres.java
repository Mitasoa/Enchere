package com.enchere.enchere.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.enchere.enchere.DAO.PhotoDAO;

public class FicheEncheres {
    int id;
    String Categorie;
    double prix;
    String produit;
    LocalDateTime datefin;
    String status;
    List<Photo> sary;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSary(List<Photo> sary) {
        this.sary = sary;
    }

    public List<Photo> getSary() {
        return sary;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getProduit() {
        return produit;
    }

    public void setDatefin(LocalDateTime datefin) {
        this.datefin = datefin;
    }

    public LocalDateTime getDatefin() {
        return datefin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void SetImage(ArrayList<FicheEncheres> fiche, PhotoDAO DAO) {
        for (int i = 0; i < fiche.size(); i++) {
            // DAO.findAll();
            // DAO.
            fiche.get(i).setSary(DAO.findAll());//// Integer.toString(fiche.get(i).getId())Sort.by({"id":fiche.get(i)})
        }
    }

}
