package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.Admin;

@Repository
public class BackOfficeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Admin> Login(Admin admin) {
        String sql = "SELECT * FROM Admin WHERE pseudo = ?  AND motdepasse = ?";
        return (ArrayList<Admin>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class),
                admin.getPseudo(), admin.getMotDePasse());
    }
}
