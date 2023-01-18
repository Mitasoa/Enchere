package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.enchere.enchere.model.HistoriqueUtilisateur;

public class HistoriqueRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<HistoriqueUtilisateur> getHistorique(int etatActuelle, int produitid, int utilisateurid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("etatActuelle").is(etatActuelle).where("produitid").is(produitid)
                .where("utilisateuridacheteur").is(utilisateurid));
        return mongoTemplate.find(query, HistoriqueUtilisateur.class);
    }

}
