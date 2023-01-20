package com.enchere.enchere.repository;

import com.enchere.enchere.model.CategorieLePlusVendu;
import com.enchere.enchere.model.CategorieRentable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategorieRentableRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<CategorieRentable> SelectAllCategorieRentable() {
        String sql = "SELECT * from TOP5_CATEGORIERENTABLE ORDER BY total DESC";
        return (ArrayList<CategorieRentable>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<CategorieRentable>(CategorieRentable.class));
    }
}
