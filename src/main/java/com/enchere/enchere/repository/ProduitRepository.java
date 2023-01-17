package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enchere.enchere.model.Produit;

public class ProduitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean TestClient(int idutilisateur, int id) throws Exception {
        String sql = "SELECT * FROM Produit where utilisateurid=? and id=?";
        ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), idutilisateur, id);
        if (tab.size() == 0) {
            return true;
        } else {
            throw new Exception("Failed Encheriser");
        }
    }

}
