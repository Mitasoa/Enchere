package com.enchere.enchere.repository;

import com.enchere.enchere.DAO.UtilisateurDAO;
import com.enchere.enchere.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepository implements UtilisateurDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur VALUES(DEFAULT,?,?,?,?)";
        jdbcTemplate.update(sql, utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getMail(),
                utilisateur.getMotdepasse());
    }
}
