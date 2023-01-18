package com.enchere.enchere.controller;


import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.model.HistoriqueUtilisateur;
import com.enchere.enchere.repository.HistoriqueUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class HistoriqueUtilisateurController {
    @Autowired
    private HistoriqueUtilisateurRepository rep;
    private Data data = new Data();
    ArrayList error = new ArrayList<>();
    String message;
    int status;

    // Select
    @RequestMapping(value = "/HistoriqueUtilisateurs/{idUtilisateur}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectHistorique(HttpServletRequest request,
            @PathVariable(value = "idUtilisateur") Integer idUtilisateur) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<HistoriqueUtilisateur> historiqueUtilisateurs = rep.SelectHistoriqueById(idUtilisateur);
            data.setData(historiqueUtilisateurs);
            System.out.println(historiqueUtilisateurs.size());
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
