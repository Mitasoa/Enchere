package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.FicheEncheres;

@Repository
public class FrontOfficerepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<FicheEncheres> getFicheProduit() {

        String sql = "SELECT * FROM ficheencheres";
        return (ArrayList<FicheEncheres>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<FicheEncheres>(FicheEncheres.class));
    }

    public ArrayList<FicheEncheres> rechercheAvancer(FicheEncheres fiche, String motCle) {
        if (motCle == null) {
            motCle = "";
        }
        String sql = "SELECT * FROM ficheencheres WHERE 1=1 ";
        if (fiche.getPrix() > 0) {
            sql += " AND prix=" + fiche.getPrix() + " ";
        }
        if (fiche.getDatefin() != null) {
            sql += " AND datefin='" + fiche.getDatefin() + "' ";
        }
        if (fiche.getCategorie() != null) {
            sql += " AND categorie='" + fiche.getCategorie() + "' ";
        }
        if (fiche.getStatus() != null) {
            sql += " AND status='" + fiche.getStatus() + "' ";
        }
        sql += " AND (categorie ILIKE '%" + motCle + "%'" + " OR produit ILIKE '%" + motCle + "%' )";
        System.out.println(sql);
        return (ArrayList<FicheEncheres>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<FicheEncheres>(FicheEncheres.class));
    }
}
