package com.enchere.enchere.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.enchere.enchere.DAO.PhotoDAO;
import com.enchere.enchere.repository.ProduitRepository;

public class FicheEncheres {
    int id;
    String Categorie;
    double prix;
    String nom;
    LocalDateTime datefin;
    String status;
    ArrayList<Photo> sary;
    int idcategorie;
    int etat;

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSary(ArrayList<Photo> sary) {
        this.sary = sary;
    }

    public ArrayList<Photo> getSary() {
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

    public void setNom(String produit) {
        this.nom = produit;
    }

    public String getProduit() {
        return nom;
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

    public void SetImage(ArrayList<FicheEncheres> fiche, ProduitRepository DAO) {
        for (int i = 0; i < fiche.size(); i++) {
            // DAO.findAll();
            // DAO.

            fiche.get(i).setSary(DAO.getPhoto());//// Integer.toString(fiche.get(i).getId())Sort.by({"id":fiche.get(i)})
        }
    }

}
