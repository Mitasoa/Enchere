package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.HistoriqueUtilisateur;

@Repository
public class HistoriqueRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<HistoriqueUtilisateur> getHistorique(int etatActuelle, int produitid, int utilisateurid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("etatActuelle").is(etatActuelle).where("produitid").is(produitid)
                .where("utilisateuridacheteur").is(utilisateurid));
        return mongoTemplate.find(query, HistoriqueUtilisateur.class);
    }

    public void UpdateHisto(int idproduit) {
        // mongoTemplate

        Query query = new Query(Criteria.where("produitid").is(idproduit));
        Update update = new Update().set("etatactuelle", 0);
        mongoTemplate.updateFirst(query, update, HistoriqueRepository.class);
    }

    public HistoriqueUtilisateur getDernierHistorique(int idproduit) {
        Query query = new Query();
        query.addCriteria(Criteria.where("etatActuelle").is(1).where("produitid").is(idproduit));
        HistoriqueUtilisateur histo = null;
        try {

            if (mongoTemplate.find(query, HistoriqueUtilisateur.class).size() != 0) {
                return mongoTemplate.find(query, HistoriqueUtilisateur.class).get(0);
            }
            return new HistoriqueUtilisateur();
        } catch (Exception e) {
            // TODO: handle exception
            histo = new HistoriqueUtilisateur();
            return histo;
        }

    }

    public List<HistoriqueUtilisateur> getHistoriqueByUtil(int idutilisateur) {
        Query query = new Query();
        query.addCriteria(Criteria.where("utilisateuridacheteur").is(idutilisateur));
        return mongoTemplate.find(query, HistoriqueUtilisateur.class);
    }

    public void InsertHistorique(HistoriqueUtilisateur histo) {
        mongoTemplate.insert(histo);
    }

    public ArrayList<HistoriqueUtilisateur> ToArrayList(List<HistoriqueUtilisateur> histo) {
        ArrayList<HistoriqueUtilisateur> array = new ArrayList<>();
        for (int i = 0; i < histo.size(); i++) {
            array.add(histo.get(i));
        }
        return array;
    }

}
