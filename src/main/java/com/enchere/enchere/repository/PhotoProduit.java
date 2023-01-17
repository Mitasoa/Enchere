package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.enchere.enchere.DAO.PhotoDAO;
import com.enchere.enchere.model.Photo;

public class PhotoProduit {

    @Autowired
    PhotoDAO rep;

    public List<Photo> FindById(int id) {
        return rep.findAll();
    }
}