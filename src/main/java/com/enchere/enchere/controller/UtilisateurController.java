package com.enchere.enchere.controller;

import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.model.Utilisateur;
import com.enchere.enchere.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository rep;
    private Data data = new Data();
    ArrayList error = new ArrayList<>();
    String message;
    int status;

    // Insert
    @RequestMapping(value = "/Utilisateurs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> insertModele(HttpServletRequest request) {
        Utilisateur retour = new Utilisateur();
        ArrayList<Data> __data = new ArrayList<>();
        try {
            retour.setNom((request.getParameter("nom")));
            retour.setPrenom((request.getParameter("prenom")));
            retour.setMail((request.getParameter("mail")));
            retour.setMotdepasse((request.getParameter("motdepasse")));
            rep.insertUtilisateur(retour);
            ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
            utilisateurs.add(retour);
            data.setData(utilisateurs);
        } catch (Exception e) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        __data.add(data);
        return __data;
    }
}
