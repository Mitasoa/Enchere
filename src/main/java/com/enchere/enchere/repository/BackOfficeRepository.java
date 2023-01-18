package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.Categorie;
import com.enchere.enchere.model.Demande;
import com.enchere.enchere.model.DemandeUtilisateur;

@Repository
public class BackOfficeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Admin> Login(Admin admin) {
        String sql = "SELECT * FROM Admin WHERE pseudo = ?  AND motdepasse = ?";
        return (ArrayList<Admin>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class),
                admin.getPseudo(), admin.getMotDePasse());
    }

    public void insertCategorie(Categorie categorie) {
        String sql = "INSERT INTO Categorie VALUES (DEFAULT,?,?)";
        jdbcTemplate.update(sql, categorie.getNom(), categorie.getDureeMax());
    }

    public void updateCategorie(Categorie categorie) {
        String sql = "UPDATE Categorie set nom = ? , dureeMax = ? WHERE id = ?";
        jdbcTemplate.update(sql, categorie.getNom(), categorie.getDureeMax(), categorie.getId());
    }

    public void deleteCategorie(int id) {
        String sql = "UPDATE Categorie set etat = -10 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public ArrayList<Categorie> ListeCategorie() {
        String sql = "SELECT * FROM Categorie WHERE etat != -10";
        return (ArrayList<Categorie>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Categorie>(Categorie.class));
    }

    public ArrayList<DemandeUtilisateur> listeDemandeUser(int etat) {
        String sql = "SELECT * FROM DemandeUtilisateur WHERE etat = ?";
        return (ArrayList<DemandeUtilisateur>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<DemandeUtilisateur>(DemandeUtilisateur.class), etat);
    }

    public void acceptDemande(int id, int idUtilisateur, float solde) {
        String sql = "UPDATE Demande set etat = 10,montant=((SELECT solde FROM Utilisateur WHERE id=?)+" + solde
                + ") WHERE id = ?";
        jdbcTemplate.update(sql, idUtilisateur, id);

    }

    public void refuseDemande(int id) {
        String sql = "UPDATE Demande set etat = -10 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateComission(double comission) {
        String sql = "INSERT INTO Comission VALUES (DEFAULT,?,DEFAULT)";
        jdbcTemplate.update(sql, comission);
    }

    public ArrayList<Demande> getListeDemande(Float montant, Integer etat) {
        String sql = "SELECT * FROM V_Demande WHERE 1 = 1 ";
        if (montant != null) {
            sql = sql + " AND montant = " + montant;
        }
        if (etat != null) {
            sql = sql + " AND etat = " + etat;
        }
        return (ArrayList<Demande>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Demande>(Demande.class));
    }

    public void repondreDemande(int idDemande, int etat) {
        String sql = "UPDATE Demande set etat ? WHERE id = ?";
        jdbcTemplate.update(sql, idDemande, etat);
    }
}
