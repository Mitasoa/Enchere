package com.enchere.enchere.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enchere.enchere.DAO.PhotoDAO;
import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.FicheEncheres;
import com.enchere.enchere.model.Photo;
import com.enchere.enchere.repository.BackOfficeRepository;
import com.enchere.enchere.repository.FrontOfficerepository;
import com.enchere.enchere.repository.LoginRepository;
import com.enchere.enchere.repository.PhotoProduit;
import com.enchere.enchere.repository.UtilisateurRepository;
import com.enchere.enchere.model.*;

@Controller
public class FrontOfficeController {

    @Autowired
    FrontOfficerepository repOFF;

    // @Autowired
    // PhotoProduit photoREP;

    @Autowired
    PhotoDAO photo;

    @Autowired
    UtilisateurRepository logREP;

    private Data data = new Data();

    @ResponseBody
    @RequestMapping(value = "/FicheEncheres", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getFicheEncheres() {
        ArrayList<FicheEncheres> ficheEncheres = repOFF.getFicheProduit();
        ArrayList<Data> __data = new ArrayList<>();
        new FicheEncheres().SetImage(ficheEncheres, photo);
        data.setData(ficheEncheres);
        __data.add(data);
        return __data;

    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> Login(HttpServletRequest request) {
        Utilisateur retour = new Utilisateur();
        retour.setMail(request.getParameter("email"));
        retour.setMotdepass(request.getParameter("mdp"));
        Token token = logREP.Login(retour);
        ArrayList<Token> TokenRes = new ArrayList<>();
        TokenRes.add(token);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(TokenRes);
        __data.add(data);
        return __data;
    }

    @ResponseBody
    @RequestMapping(value = "/FicheEncheres", method = RequestMethod.POST, produces = "application/json")
    public ArrayList<Data> rechercheAvance(HttpServletRequest request) {
        float prix = -1;
        if (request.getParameter("prix") != null) {
            prix = Float.parseFloat(request.getParameter("prix"));
        }
        FicheEncheres fiche = new FicheEncheres();
        fiche.setCategorie(request.getParameter("categorie"));
        fiche.setProduit(request.getParameter("produit"));
        fiche.setStatus(request.getParameter("status"));
        if (request.getParameter("dateFin") != null) {
            fiche.setDatefin(LocalDateTime.parse(request.getParameter("dateFin")));
        }
        fiche.setPrix(prix);
        ArrayList<FicheEncheres> ficheEncheres = repOFF.rechercheAvancer(fiche, request.getParameter("motCle"));
        ArrayList<Data> __data = new ArrayList<>();
        new FicheEncheres().SetImage(ficheEncheres, photo);
        data.setData(ficheEncheres);
        __data.add(data);
        return __data;

    }

    @RequestMapping(value = "/photo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Optional<Photo> getPhoto() {
        // photo.
        return photo.findById("{id:" + 1 + "}");
    }
}
