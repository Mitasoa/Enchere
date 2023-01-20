package com.enchere.enchere.repository;

import com.enchere.enchere.DAO.HistoriqueUtilisateurDAO;
import com.enchere.enchere.model.HistoriqueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class HistoriqueUtilisateurRepository implements HistoriqueUtilisateurDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<HistoriqueUtilisateur> SelectHistoriqueById(int idUtilisateur) {
        String sql = "SELECT H.ID,H.NOM,H.PRIX,H.DATEENCHERISER,H.DUREE,C.NOM AS CATEGORIE FROM HISTORIQUEUTILISATEUR H JOIN CATEGORIE C ON C.ID = H.CATEGORIEID WHERE UTILISATEURID=?";
        return (ArrayList<HistoriqueUtilisateur>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<HistoriqueUtilisateur>(HistoriqueUtilisateur.class), idUtilisateur);
    }
}
