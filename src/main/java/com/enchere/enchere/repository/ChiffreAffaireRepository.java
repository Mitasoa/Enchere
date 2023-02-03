package com.enchere.enchere.repository;

import com.enchere.enchere.model.CategorieRentable;
import com.enchere.enchere.model.ChiffreAffaire;
import com.enchere.enchere.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ChiffreAffaireRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<ChiffreAffaire> SelectChiffreAffaireJour() {
        String sql = "SELECT*from CAM_PARJOUR";
        return (ArrayList<ChiffreAffaire>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<ChiffreAffaire>(ChiffreAffaire.class));
    }

    public ArrayList<ChiffreAffaire> SelectChiffreAffaireMois() {
        String sql = "SELECT*from CAM_PARMOIS";
        return (ArrayList<ChiffreAffaire>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<ChiffreAffaire>(ChiffreAffaire.class));
    }

    public ArrayList<ChiffreAffaire> SelectChiffreAffaireAn() {
        String sql = "SELECT*from CAM_PARAN";
        return (ArrayList<ChiffreAffaire>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<ChiffreAffaire>(ChiffreAffaire.class));
    }

    public void execute() {
        String sql = "SELECT * FROM enchere WHERE  etat = 0 AND status = 'termine' ";
        ArrayList<Produit> produits = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class));
        System.out.println(produits.size());
        for (int i = 0; i < produits.size(); i++) {
            double prix = ((Produit) produits.get(i)).getPrix();
            int id = ((Produit) produits.get(i)).getId();
            String insert = "INSERT INTO Commission VALUES (DEFAULT,((SELECT taux FROM TauxComission ORDER BY date DESC LIMIT 1)/100)*("
                    + prix + "),DEFAULT)";
            String update = "UPDATE Produit set etat = 1 WHERE id = ?";
            jdbcTemplate.update(insert);
            jdbcTemplate.update(update, id);
        }
    }
}