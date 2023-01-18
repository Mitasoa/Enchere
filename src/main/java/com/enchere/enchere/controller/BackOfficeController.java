package com.enchere.enchere.controller;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.model.Categorie;
import com.enchere.enchere.model.DemandeUtilisateur;
import com.enchere.enchere.repository.BackOfficeRepository;

@Controller
public class BackOfficeController {
    @Autowired
    BackOfficeRepository backRep;

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
        return "View/login";
    }

    @RequestMapping("/Categories")
    public String addCategorie(Model model, HttpServletRequest request) {
        Categorie cat = new Categorie();
        if (request.getParameter("nom") != null && request.getParameter("dureeMax") != null) {
            cat.setNom(request.getParameter("nom"));
            cat.setDureeMax(LocalTime.parse((request.getParameter("dureeMax"))));
            backRep.insertCategorie(cat);
            model.addAttribute("succes", "Insertion effectuée !");
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
        return "View/index";
    }

    @RequestMapping("/ListeDemandes")
    public String demande(Model model, HttpServletRequest request) {
        int etat = 0;
        if (request.getParameter("accept") != null && request.getParameter("idUser") != null
                && request.getParameter("solde") != null) {
            backRep.acceptDemande(Integer.parseInt(request.getParameter("accept")),
                    Integer.parseInt(request.getParameter("idUser")), Float.parseFloat(request.getParameter("solde")));
        }
        if (request.getParameter("no") != null) {
            backRep.refuseDemande(Integer.parseInt(request.getParameter("no")));
        }
        if (request.getParameter("valide") != null) {
            etat = 10;
        }
        if (request.getParameter("refuse") != null) {
            etat = -10;
        }
        ArrayList<DemandeUtilisateur> allDemandeUser = backRep.listeDemandeUser(etat);
        model.addAttribute("allDemandeUser", allDemandeUser);
        model.addAttribute("content", "demande");
        model.addAttribute("contentpath", "View/demande");
        return "View/index";
    }

    @RequestMapping("/Statistique")
    public String stat(Model model, HttpServletRequest request) {
        model.addAttribute("content", "statistique");
        model.addAttribute("contentpath", "View/statistique");
        return "View/index";
    }
}
