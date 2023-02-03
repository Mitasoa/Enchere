package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.HistoriqueUtilisateur;

@Repository
public class HistoriqueRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public ArrayList<HistoriqueUtilisateur> getHistoActus(int idutil) {
        String sql = "select * from historiqueutilisateur where utilisateuridacheteur=? and etatactuelle=1";
        ArrayList<HistoriqueUtilisateur> tab = (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), idutil);
        return tab;
    }

    public void UpdateHisto(int idproduit) {
        // mongoTemplate
        String sql = "update historiqueutilisateur set etatactuelle=20 where produitid=?";
        jdbc.update(sql, idproduit);
    }

    public void InsertHistorique(HistoriqueUtilisateur histo) {
        // nom | prix | utilisateuridvendeur | utilisateuridacheteur | categorieid |
        // dateencheriser | duree
        String sql = "insert into historiqueutilisateur(nom,prix,utilisateuridvendeur,utilisateuridacheteur,categorieid,dateencheriser,duree,produitid)values(?,?,?,?,?,?,?,?)";
        jdbc.update(sql, histo.getNom(), histo.getPrix(), histo.getUtilisateuridvendeur(),
                histo.getUtilisateuridacheteur(), histo.getCategorieid(), histo.getDateencheriser(), histo.getDuree(),
                histo.getProduitid());
    }

    public HistoriqueUtilisateur getDernierHistorique(int idproduit) {
        String sql = "select * from historiqueutilisateur where produitid=? and etatActuelle=1";
        ArrayList<HistoriqueUtilisateur> histo = (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), idproduit);

        if (histo != null) {
            try {
                return histo.get(0);
            } catch (Exception e) {
                return new HistoriqueUtilisateur();
                // TODO: handle exception
            }
        }
        return new HistoriqueUtilisateur();
    }

    public ArrayList<HistoriqueUtilisateur> getHistoriqueBYID(int idproduit) {
        String sql = "select * from historiqueutilisateur where produitid=?";

        return (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), idproduit);
    }

    public ArrayList<HistoriqueUtilisateur> getHistoriqueUsers(int iduser) {
        String sql = "select * from historiqueutilisateur where utilisateuridvendeur=? or utilisateuridacheteur=?";

        return (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), iduser, iduser);
    }

    public ArrayList<HistoriqueUtilisateur> getPropreHistorique(int iduser) {
        String sql = "select * from C_HistoriqueUtilisateur where utilisateuridvendeur=?";
        return (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), iduser);
    }

    public ArrayList<HistoriqueUtilisateur> getHistoriqueNormale(int iduser) {
        String sql = "select * from C_historiqueUtilisateur where utilisateuridacheteur=?";
        return (ArrayList<HistoriqueUtilisateur>) jdbc.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), iduser);

    }

}
