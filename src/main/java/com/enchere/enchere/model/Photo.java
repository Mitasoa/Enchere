package com.enchere.enchere.model;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Photo")
public class Photo {
    @Id
    private ObjectId _id;

    int id;
    String sary;
    int idproduit;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSary() {
        return this.sary;
    }

    public void setSary(String ph) {
        this.sary = ph;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdproduit() {

        return idproduit;
    }

   
}
