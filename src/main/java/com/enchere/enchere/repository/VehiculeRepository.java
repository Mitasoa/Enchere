package com.enchere.enchere.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.DAO.VehiculeDAO;
import com.enchere.enchere.model.Vehicule;

@Repository
public class VehiculeRepository implements VehiculeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Vehicule> SelectAll() {
        String sql = "SELECT * from Vehicule";
        return (ArrayList<Vehicule>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Vehicule>(Vehicule.class));
    }

    @Override
    public void Save(Vehicule vehicule) {
        String sql = "INSERT INTO vehicule VALUES (DEFAULT,?,?,?,?,DEFAULT)";
        jdbcTemplate.update(sql, vehicule.getMarqueid(), vehicule.getModelid(), vehicule.getSerieid(),
                vehicule.getImmatriculation());
    }

    @Override
    public void Update(Vehicule vehicule) {
        String sql = "UPDATE vehicule set marqueid=?,modelid=?,serieid=?,immatriculation=? WHERE id=?";
        jdbcTemplate.update(sql, vehicule.getMarqueid(), vehicule.getModelid(), vehicule.getSerieid(),
                vehicule.getImmatriculation(), vehicule.getId());
    }

    @Override
    public void Delete(Vehicule vehicule) {
        String sql = "UPDATE vehicule set etat=1 WHERE id=?";
        jdbcTemplate.update(sql, vehicule.getId());

    }

    @Override
    public ArrayList<Vehicule> SelectByIdVehicule(int id) {
        String sql = "SELECT * from Vehicule WHERE id=?";
        return (ArrayList<Vehicule>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Vehicule>(Vehicule.class), id);
    }

}
