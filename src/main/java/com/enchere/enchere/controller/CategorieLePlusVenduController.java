package com.enchere.enchere.controller;

import com.enchere.enchere.model.CategorieLePlusVendu;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.repository.CategorieLePlusVenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CategorieLePlusVenduController {
    @Autowired
    private CategorieLePlusVenduRepository rep;
    private Data data = new Data();
    ArrayList error = new ArrayList<>();
    String message;
    int status;

    // Select
    @RequestMapping(value = "/CategorieLePlusVendus", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectCategorie(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<CategorieLePlusVendu> categorieLePlusVendus = rep.SelectAllCategorie();
            data.setData(categorieLePlusVendus);
            System.out.println(categorieLePlusVendus.size());
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
