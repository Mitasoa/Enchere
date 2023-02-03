package com.enchere.enchere.controller;

import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.Categorie;
import com.enchere.enchere.model.CategorieLePlusVendu;
import com.enchere.enchere.model.CategorieRentable;
import com.enchere.enchere.model.ChiffreAffaire;
import com.enchere.enchere.model.DemandeUtilisateur;
import com.enchere.enchere.repository.BackOfficeRepository;
import com.enchere.enchere.repository.CategorieLePlusVenduRepository;
import com.enchere.enchere.repository.CategorieRentableRepository;
import com.enchere.enchere.repository.ChiffreAffaireRepository;

@Controller
public class BackOfficeController {
    @Autowired
    BackOfficeRepository backRep;

    @Autowired
    private ChiffreAffaireRepository rep;

    @Autowired
    private CategorieLePlusVenduRepository catRep;

    @Autowired
    private CategorieRentableRepository catRent;

    NumberFormat formatage = NumberFormat.getInstance(Locale.ENGLISH);

    @RequestMapping("/")
    public String Login(Model model, HttpServletRequest request) {
        Admin admin = new Admin();
        if (request.getParameter("pseudo") != null && request.getParameter("mdp") != null) {
            admin.setPseudo(request.getParameter("pseudo"));
            admin.setMotDePasse(request.getParameter("mdp"));
            ArrayList __admin = backRep.Login(admin);
            if (__admin.size() != 0) {
                return "redirect:Categories";
            } else {
                model.addAttribute("error", "Compte inexistant !");
            }
        }
        model.addAttribute("content", "login");
        model.addAttribute("contentpath", "View/login");
        rep.execute();
        return "View/login";
    }

    @RequestMapping("/Categories")
    public String addCategorie(Model model, HttpServletRequest request) {
        Categorie cat = new Categorie();
        if (request.getParameter("nom") != null && request.getParameter("dureeMax") != null) {
            cat.setNom(request.getParameter("nom"));
            cat.setDureeMax(LocalTime.parse((request.getParameter("dureeMax"))));
            ArrayList verif = backRep.verifCategorie(request.getParameter("nom"));
            if(verif.size() == 0){
                backRep.insertCategorie(cat);
                model.addAttribute("succes", "Insertion effectuée !");
            }else {
                model.addAttribute("error", "Catégorie existe déjà !");
            }
            
        }
        if (request.getParameter("categorie") != null && request.getParameter("duree") != null
                && request.getParameter("id") != null) {
            cat.setNom(request.getParameter("categorie"));
            cat.setId(Integer.parseInt(request.getParameter("id")));
            cat.setDureeMax(LocalTime.parse(request.getParameter("duree")));
            backRep.updateCategorie(cat);
            model.addAttribute("succes", "Mise à jour effectuée !");
        }
        if (request.getParameter("idCategorie") != null) {
            backRep.deleteCategorie(Integer.parseInt(request.getParameter("idCategorie")));
            model.addAttribute("succes", "Supression effectuée !");
        }
        ArrayList<Categorie> allCategorie = backRep.ListeCategorie();
        model.addAttribute("allCategorie", allCategorie);
        model.addAttribute("content", "categories");
        model.addAttribute("contentpath", "View/categories");
        rep.execute();
        return "View/index";
    }

    @RequestMapping("/ListeDemandes")
    public String demande(Model model, HttpServletRequest request) {
        int etat = 0;
        boolean isOk = true;
        if (request.getParameter("accept") != null && request.getParameter("idUser") != null
                && request.getParameter("solde") != null) {
            backRep.acceptDemande(Integer.parseInt(request.getParameter("accept")),
                    Integer.parseInt(request.getParameter("idUser")), Float.parseFloat(request.getParameter("solde")));
        }
        if (request.getParameter("no") != null) {
            backRep.refuseDemande(Integer.parseInt(request.getParameter("no")));
        }
        if (request.getParameter("valide") != null) {
            isOk = false;
            etat = 10;
        }
        if (request.getParameter("refuse") != null) {
            isOk = false;
            etat = -10;
        }
        ArrayList<DemandeUtilisateur> allDemandeUser = backRep.listeDemandeUser(etat);
        model.addAttribute("allDemandeUser", allDemandeUser);
        model.addAttribute("isOk", isOk);
        model.addAttribute("content", "demande");
        model.addAttribute("contentpath", "View/demande");
        rep.execute();
        return "View/index";
    }

    @RequestMapping("/Statistique")
    public String stat(Model model, HttpServletRequest request) {
        ArrayList jour = rep.SelectChiffreAffaireJour();
        ArrayList mois = rep.SelectChiffreAffaireMois();
        ArrayList annee = rep.SelectChiffreAffaireAn();
        ArrayList<CategorieLePlusVendu> categorieLePlusVendus = catRep.SelectAllCategorie();
        ArrayList<CategorieRentable> categorieRentables = catRent.SelectAllCategorieRentable();
        model.addAttribute("jour", formatage.format(((ChiffreAffaire) jour.get(0)).getMoyenne()) + " Ariary");
        model.addAttribute("mois", formatage.format(((ChiffreAffaire) mois.get(0)).getMoyenne()) + " Ariary");
        model.addAttribute("annee", formatage.format(((ChiffreAffaire) annee.get(0)).getMoyenne()) + " Ariary");
        model.addAttribute("categorieLePlusVendus", categorieLePlusVendus);
        model.addAttribute("categorieRentables", categorieRentables);
        model.addAttribute("content", "statistique");
        model.addAttribute("contentpath", "View/statistique");
        rep.execute();
        return "View/index";
    }

    @RequestMapping("/Parametrage")
    public String param(Model model, HttpServletRequest request) {
        if (request.getParameter("taux") != null) {
            try {
                backRep.insertTaux(Float.parseFloat(request.getParameter("taux")));
                model.addAttribute("success", "Taux ajouter avec succès");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "Une erreur s'est produite !");
            }
        }
        ArrayList allParam = backRep.taux();
        model.addAttribute("allParam", allParam);
        model.addAttribute("content", "parametrage");
        model.addAttribute("contentpath", "View/parametrage");
        rep.execute();
        return "View/index";
    }
}
