package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.enchere.enchere.model.Demande;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.model.Produit1;
import com.enchere.enchere.model.Utilisateur;

@Repository
public class MobileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inscrire(Utilisateur utilisateur) {
        String sql = "INSERT INTO Utilisateur VALUES (DEFAULT,?,?,?,?,?)";
        jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getMail(),
                utilisateur.getMotdepass(), 0);
    }

    public ArrayList<Produit> getEnchere(int id) {
        String sql = "SELECT * FROM Enchere WHERE utilisateurid=?";
        return (ArrayList<Produit>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Produit>(Produit.class), id);
    }

    public void recharger(Demande demande) {
        String sql = "INSERT INTO Demande (idutilisateur,montant, datedemande) VALUES (?,?,?)";
        jdbcTemplate.update(sql, demande.getIdUtilisateur(), demande.getMontant(), demande.getDateDemande());
    }

    public void addProduit(Produit produit) {
        String sql = "INSERT INTO Produit VALUES (DEFAULT,?,?,?,?,DEFAULT,?,DEFAULT)";
        jdbcTemplate.update(sql, produit.getNom(), produit.getPrix(), produit.getUtilisateurid(),
                produit.getCategorieid(), produit.getDuree());
    }

    public ArrayList<Utilisateur> connecter(Utilisateur user) {
        String sql = "SELECT * FROM Utilisateur WHERE mail=? AND motdepasse=?";
        return (ArrayList<Utilisateur>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class), user.getMail(), user.getMotdepass());
    }
}
