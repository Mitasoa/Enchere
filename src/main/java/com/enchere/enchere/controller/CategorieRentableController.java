package com.enchere.enchere.controller;

import com.enchere.enchere.model.CategorieRentable;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.repository.CategorieRentableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CategorieRentableController {
    @Autowired
    private CategorieRentableRepository rep;
    private Data data = new Data();
    ArrayList error = new ArrayList<>();
    String message;
    int status;

    // Select
    @RequestMapping(value = "/CategorieRentables", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectCategorie(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<CategorieRentable> categorieRentables = rep.SelectAllCategorieRentable();
            data.setData(categorieRentables);
            System.out.println(categorieRentables.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }
}
