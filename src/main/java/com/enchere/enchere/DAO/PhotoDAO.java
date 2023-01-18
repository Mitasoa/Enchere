package com.enchere.enchere.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.enchere.enchere.model.Photo;

public interface PhotoDAO extends MongoRepository<Photo, String> {

    /// public List<Photo> FindById(int id);

}
