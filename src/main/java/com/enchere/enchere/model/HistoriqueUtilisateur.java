package com.enchere.enchere.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.xml.namespace.QName;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HistoriqueUtilisateur")
public class HistoriqueUtilisateur {
    // id | nom | prix | utilisateuridvendeur | utilisateuridacheteur | categorieid
    // | dateencheriser | duree

    @Id
    private ObjectId _id;

    int id;
    String nom;
    double prix;
    int utilisateuridvendeur;
    int utilisateuridacheteur;
    int categorieid;
    String dateencheriser;
    String duree;
    int etatActuelle;
    int produitid;

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setEtatActuelle(int etatActuelle) {
        this.etatActuelle = etatActuelle;
    }

    public int getEtatActuelle() {
        return etatActuelle;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public void setDateencheriser(String dateencheriser) {
        // this.dateencheriser = LocalDateTime.parse(dateencheriser.replace(" ", "T"));
        this.dateencheriser = dateencheriser;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setUtilisateuridacheteur(int utilisateuridacheteur) {
        this.utilisateuridacheteur = utilisateuridacheteur;
    }

    public void setUtilisateuridvendeur(int utilisateuridvendeur) {
        this.utilisateuridvendeur = utilisateuridvendeur;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public String getDateencheriser() {
        return dateencheriser;
    }

    public String getDuree() {
        return duree;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getUtilisateuridacheteur() {
        return utilisateuridacheteur;
    }

    public int getUtilisateuridvendeur() {
        return utilisateuridvendeur;
    }

    /*
     * Query query = new Query();
     * query.addCriteria(Criteria.where("nomColonne").is("valeurRecherchée"));
     * List<VotreClasseDeModèle> resultats = mongoTemplate.find(query,
     * VotreClasseDeModèle.class);
     */

}