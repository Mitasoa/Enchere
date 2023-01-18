package com.enchere.enchere.repository;

import com.enchere.enchere.model.CategorieRentable;
import com.enchere.enchere.model.ChiffreAffaire;
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
}