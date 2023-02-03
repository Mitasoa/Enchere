package com.enchere.enchere.controller;

import com.enchere.enchere.model.ChiffreAffaire;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.repository.ChiffreAffaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ChiffreAffaireController {
    @Autowired
    private ChiffreAffaireRepository rep;
    private Data data = new Data();
    ArrayList error = new ArrayList<>();
    String message;
    int status;

    // Select
    @RequestMapping(value = "/ChiffreAffaires/jour", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectChiffreAffaireJour(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<ChiffreAffaire> chiffreAffaires = rep.SelectChiffreAffaireJour();
            data.setData(chiffreAffaires);
            System.out.println(chiffreAffaires.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        } finally {
            rep.execute();
        }
        __data.add(data);
        return __data;
    }

    // Select
    @RequestMapping(value = "/Notification", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> notif(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<Produit> cA = rep.execute();
            data.setData(cA);
            System.out.println(cA.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }

    // Select
    @RequestMapping(value = "/NotificationTermine", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> getNotif(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<Produit> cA = rep.notif();
            data.setData(cA);
            System.out.println(cA.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }

    // Select
    @RequestMapping(value = "/ChiffreAffaires/mois", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectChiffreAffaireMois(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<ChiffreAffaire> chiffreAffaires = rep.SelectChiffreAffaireMois();
            data.setData(chiffreAffaires);
            System.out.println(chiffreAffaires.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        } finally {
            rep.execute();
        }
        __data.add(data);
        return __data;
    }

    // Select
    @RequestMapping(value = "/ChiffreAffaires/an", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> SelectChiffreAffaireAn(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<ChiffreAffaire> chiffreAffaires = rep.SelectChiffreAffaireAn();
            data.setData(chiffreAffaires);
            System.out.println(chiffreAffaires.size());
        } catch (Exception ex) {
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        } finally {
            rep.execute();
        }
        __data.add(data);
        return __data;
    }

    // Select
    @RequestMapping(value = "/Comissions", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> executeCommission() {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            rep.execute();
            error.add("Execution sans erreur");
        } catch (Exception ex) {
            ex.printStackTrace();
            status = 500;
            message = "Error d'acces base de donnee detecter";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }
}
