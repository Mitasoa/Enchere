package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.DAO.PhotoDAO;
import com.enchere.enchere.model.Photo;

@Repository
public class PhotoProduit {

    @Autowired
    JdbcTemplate jdbc;

    public ArrayList<Photo> GetPhoto(int idproduit) {
        String sql = "select * from photo where idproduit=?";
        return (ArrayList<Photo>) jdbc.query(sql,
                new BeanPropertyRowMapper<Photo>(Photo.class), idproduit);
    }

    public String InsertPhoto(Photo[] photo) {

        try {
            // jdbc.update(sql, 24, "photo[0].getSary()");
            for (int i = 0; i < photo.length; i++) {
                // if (photo[i].getSary() != null && photo[i].getSary() != "") {
                // String sql = "insert into photo(idproduit,sary)values('" +
                // photo[i].getIdproduit() + "','"
                // + photo[i].getSary() + "')";
                // System.out.print("DATA====>" + photo[i].getIdproduit() + "," +
                // photo[i].getSary());
                // jdbc.update(sql);
                // }
            }
            return "Success";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public Photo[] ToPhoto(int idproduit, String json) {
        int length = 0;
        if (json != "{}") {
            length = json.split(",").length - 1;
        }
        Photo[] photo = new Photo[length];
        System.out.println(length);
        String json1 = json.replace("{", "");
        String json2 = json1.replace("}", "");
        String json3 = json2.replace("\"", "");
        String[] sary = json3.split(",");
        for (int i = 0; i < length; i++) {
            Photo p = new Photo();
            p.setIdproduit(idproduit);
            p.setSary(sary[i]);
            photo[i] = p;
            if (sary[i].length() < 50) { // Ligne
                String sql = "insert into photo(idproduit,sary)values('" +
                        idproduit + "','"
                        + sary[i] + "')";
                jdbc.update(sql);
            }
        }
        return photo;
    }
}