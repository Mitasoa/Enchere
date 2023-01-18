package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.Token;
import com.enchere.enchere.model.Utilisateur;

@Repository
public class UtilisateurRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // /*public Utilisateur Login(String mail, String pwd) {

    // }*/

    public Token Login(Utilisateur login) {
        Token tokenVR = null;
        String sql = "select * from utilisateur where mail=? and motdepasse=?";
        ArrayList<Utilisateur> result = (ArrayList<Utilisateur>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class), login.getMail(), login.getMotdepass());
        int idutil = 0;
        if (result.size() > 0) {
            idutil = result.get(0).getId();
        }
        if (idutil != 0) {
            tokenVR = new Token().ReturnToken(idutil);
        }
        return tokenVR;
    }

    public Utilisateur getOneUtilisateur(int id) {
        String sql = "select * from utilisateur where id=?";
        ArrayList<Utilisateur> result = (ArrayList<Utilisateur>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class), id);
        return result.get(0);
    }
}
