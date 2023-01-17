package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enchere.enchere.model.Login;
import com.enchere.enchere.model.Token;

public class LoginRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inscription(Login login) {
        String sql = "INSERT INTO utilisateur VALUES (DEFAULT,?,md5(?))";
        jdbcTemplate.update(sql, login.getLogin(), login.getMdp());
    }

    public Token Login(Login login) {
        Token tokenVR = null;
        String sql = "select * from utilisateur where mail=? and motdepasse=md5(?)";
        ArrayList<Login> result = (ArrayList<Login>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Login>(Login.class), login.getLogin(), login.getMdp());
        int idutil = 0;
        if (result.size() > 0) {
            idutil = result.get(0).getId();
        }
        if (idutil != 0) {
            tokenVR = new Token().ReturnToken(idutil);
        }
        return tokenVR;
    }
}
