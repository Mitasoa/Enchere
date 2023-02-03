package com.enchere.enchere.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.Categorie;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Demande;
import com.enchere.enchere.model.DemandeUtilisateur;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.model.Utilisateur;
import com.enchere.enchere.repository.MobileRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import com.enchere.enchere.repository.ChiffreAffaireRepository;

@Controller
public class MobileController {
    @Autowired
    MobileRepository mobileRep;

    private Data data = new Data();
    String message;
    int status;

    @Autowired
    private ChiffreAffaireRepository rep;

    @RequestMapping(value = "/Demandes", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> DemandeRechargement(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        ArrayList error = new ArrayList<>();
        Demande demande = new Demande();
        if (request.getParameter("idUtilisateur") != null && request.getParameter("montant") != null) {
            try {
                demande.setMontant(Float.parseFloat(request.getParameter("montant")));
                demande.setIdUtilisateur(Integer.parseInt(request.getParameter("idUtilisateur")));
                demande.setEtat("en cours");
                demande.setDateDemande(LocalDateTime.now());
                demande.setId(0);
                mobileRep.recharger(demande);
                ArrayList __demande = new ArrayList<>();
                __demande.add(demande);
                data.setData(__demande);
            } catch (Exception e) {
                status = 500;
                message = "Une erreur s'est produite : " + e;
                Erreur __error = new Erreur(status, message);
                error.add(__error);
                data.setData(error);
            } finally {
                rep.execute();
            }

        } else {
            status = 500;
            message = "il y une ou plusieurs valeur nulle !";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }

    @RequestMapping(value = "/GetCategorie", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> getCategorie(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        ArrayList error = new ArrayList<>();
        Categorie categorie = new Categorie();
        try {
            ArrayList<Categorie> __cat = mobileRep.getCategorie();
            data.setData(__cat);
        } catch (Exception e) {
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        } finally {
            rep.execute();
        }
        __data.add(data);
        return __data;
    }

    @RequestMapping(value = "/Encheres/{idUtilisateur}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> getEnchere(@PathVariable(value = "idUtilisateur") int idUtilisateur) {
        ArrayList<Data> __data = new ArrayList<>();
        ArrayList error = new ArrayList<>();
        try {
            ArrayList<Produit> mesEncheres = mobileRep.getEnchere(idUtilisateur);
            for (int i = 0; i < mesEncheres.size(); i++) {
                mesEncheres.get(i).setPhoto1(mobileRep);
            }
            data.setData(mesEncheres);
        } catch (Exception e) {
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        } finally {
            rep.execute();
        }
        __data.add(data);
        return __data;
    }

    @RequestMapping(value = "/Utilisateurs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> inscription(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        ArrayList error = new ArrayList<>();
        Utilisateur user = new Utilisateur();
        if (request.getParameter("nom") != null && request.getParameter("prenom") != null
                && request.getParameter("mail") != null && request.getParameter("motdepasse") != null) {
            try {
                user.setNom(request.getParameter("nom"));
                user.setPrenom(request.getParameter("prenom"));
                user.setMail(request.getParameter("mail"));
                user.setMotdepass(request.getParameter("motdepasse"));
                user.setId(0);
                user.setSolde(0);
                mobileRep.inscrire(user);
                user.setMotdepass("*");
                ArrayList __user = new ArrayList<>();
                __user.add(user);
                data.setData(__user);
            } catch (Exception e) {
                status = 500;
                message = "Une erreur s'est produite : " + e;
                Erreur __error = new Erreur(status, message);
                error.add(__error);
                data.setData(error);
            } finally {
                rep.execute();
            }

        } else {
            status = 500;
            message = "il y une ou plusieurs valeur nulle !";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> connecter(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        ArrayList error = new ArrayList<>();
        __data.clear();
        error.clear();
        Utilisateur user = new Utilisateur();
        if (request.getParameter("mail") != null && request.getParameter("motdepasse") != null) {
            try {
                user.setMail(request.getParameter("mail"));
                user.setMotdepass(request.getParameter("motdepasse"));
                mobileRep.connecter(user);
                ArrayList<Utilisateur> __user = mobileRep.connecter(user);
                user.setMotdepass("*");
                data.setData(__user);
                if (__user.size() == 0) {
                    status = 501;
                    message = "Mot de passe incorrect";
                    Erreur __error = new Erreur(status, message);
                    error.add(__error);
                    data.setData(error);
                }
            } catch (Exception e) {
                status = 500;
                message = "Une erreur s'est produite : " + e;
                Erreur __error = new Erreur(status, message);
                error.add(__error);
                data.setData(error);
            } finally {
                rep.execute();
            }

        } else {
            status = 500;
            message = "il y une ou plusieurs valeur nulle !";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
        }
        __data.add(data);
        return __data;
    }

}
