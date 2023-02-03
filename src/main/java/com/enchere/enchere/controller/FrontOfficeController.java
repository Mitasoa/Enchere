package com.enchere.enchere.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enchere.enchere.DAO.HistoriqueDAO;
import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Erreur;
import com.enchere.enchere.model.EtatSolde;
import com.enchere.enchere.model.FicheEncheres;
import com.enchere.enchere.model.HistoriqueUtilisateur;
import com.enchere.enchere.model.Photo;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.model.Token;
import com.enchere.enchere.model.Utilisateur;
import com.enchere.enchere.repository.FrontOfficerepository;
import com.enchere.enchere.repository.HistoriqueRepository;
import com.enchere.enchere.repository.PhotoProduit;
import com.enchere.enchere.repository.ProduitRepository;
import com.enchere.enchere.repository.UtilisateurRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FrontOfficeController {

    @Autowired
    FrontOfficerepository repOFF;

    @Autowired
    ProduitRepository ProdREP;

    @Autowired
    PhotoProduit photo;

    @Autowired
    HistoriqueDAO histo;

    @Autowired
    UtilisateurRepository logREP;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HistoriqueRepository HistoREP;

    ArrayList error = new ArrayList<>();
    String message;
    int status;
    /*
     * @Autowired
     * ProduitRepository prodREP;
     */
    @Autowired
    ProduitRepository rep;

    private Data data = new Data();
    ///// https://enchere-production.up.railway.app

    @ResponseBody
    @RequestMapping(value = "/FicheEncheres", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getFicheEncheres() {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<FicheEncheres> ficheEncheres = repOFF.getFicheProduit();
            new FicheEncheres().SetImage(ficheEncheres, ProdREP);
            data.setData(ficheEncheres);
            __data.add(data);
        } catch (Exception e) {
            // TODO: handle exception
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return __data;

    }

    @ResponseBody
    @RequestMapping(value = "/FicheEncheres/{offset}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getFicheEncheresPAGINE(@PathVariable int offset) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
            ArrayList<FicheEncheres> ficheEncheres = repOFF.getFicheProduitPAGINE(offset);
            new FicheEncheres().SetImage(ficheEncheres, ProdREP);
            data.setData(ficheEncheres);
            __data.add(data);
        } catch (Exception e) {
            // TODO: handle exception
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return __data;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public ArrayList<Data> Login(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        if (request.getParameter("email") != null && request.getParameter("mdp") != null) {
            try {
                Utilisateur retour = new Utilisateur();
                retour.setMail(request.getParameter("email"));
                retour.setMotdepass(request.getParameter("mdp"));
                Token token = logREP.Login(retour);
                ArrayList<Token> TokenRes = new ArrayList<>();
                TokenRes.add(token);

                data.setData(TokenRes);
                __data.add(data);
            } catch (Exception e) {
                // TODO: handle exception
                status = 500;
                message = "Une erreur s'est produite : " + e;
                Erreur __error = new Erreur(status, message);
                error.add(__error);
                data.setData(error);
            }
        } else {
            // TODO: handle exception
            status = 500;
            message = "email ou votre mot de passe est vide";
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return __data;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/FicheEncheres", method = RequestMethod.POST, produces = "application/json")
    public ArrayList<Data> rechercheAvance(HttpServletRequest request) {
        ArrayList<Data> __data = new ArrayList<>();
        try {
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

            new FicheEncheres().SetImage(ficheEncheres, ProdREP);
            data.setData(ficheEncheres);
            __data.add(data);
        } catch (Exception e) {
            // TODO: handle exception
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return __data;
    }

    /*
     * @RequestMapping(value = "/photo", method = RequestMethod.GET, produces =
     * "application/json")
     * 
     * @ResponseBody
     * public Optional<Photo> getPhoto() {
     * // photo.
     * return photo.findById("{id:" + 1 + "}");
     * }
     */

    /*
     * @RequestMapping(value = "/histo/{nom}", method = RequestMethod.GET, produces
     * = "application/json")
     * 
     * @ResponseBody
     * public List<HistoriqueUtilisateur> Histo(@PathVariable(value = "nom") String
     * param) {
     * // return histo.findAll();
     * // Query query = new Query();
     * // query.addCriteria(Criteria.where("nom").is(param).where("prix").is(0));
     * // return mongoTemplate.find(query, HistoriqueUtilisateur.class);
     * Query query = new Query();
     * query.addCriteria(Criteria.where("etatActuelle").is(1).where("produitid").is(
     * 2)
     * .where("utilisateuridacheteur").is(4));
     * return mongoTemplate.find(query, HistoriqueUtilisateur.class);
     * }
     * 
     * @RequestMapping(value = "/TEST/{nom}", method = RequestMethod.GET, produces =
     * "application/json")
     * 
     * @ResponseBody
     * public EtatSolde TEST(@PathVariable(value = "nom") String param) {
     * // HistoriqueUtilisateur
     * /// ProduitRepository rep = new ProduitRepository();
     * HistoriqueUtilisateur historique = new HistoriqueUtilisateur();
     * EtatSolde sl = new EtatSolde();
     * try {
     * sl = rep.TestBlocked(1, 2, 4, 200400);
     * historique = HistoREP.getDernierHistorique(2);
     * sl = rep.FaireEncherir(historique);
     * return sl;
     * } catch (Exception e) {
     * // TODO Auto-generated catch block
     * sl.setSituation(e.toString());
     * // System.out.print(ex)
     * e.printStackTrace();
     * // return sl;
     * // e.printStackTrace();
     * }
     * return sl;
     * // return "success";
     * }
     */

    @RequestMapping(value = "/Encherir/{prix}&&{produitid}&&{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> Enherire(@PathVariable(value = "prix") double prix,
            @PathVariable(value = "produitid") int produitid,
            @PathVariable(value = "id") int id) {
        // Token tok = new Token().ToToken(token);
        long date = System.currentTimeMillis();
        Date dt = new Date(date);
        Produit produit = ProdREP.getById(produitid);
        HistoriqueUtilisateur historique = null;
        historique = HistoREP.getDernierHistorique(produitid);
        historique.setPrix(prix);
        historique.setNom(produit.getNom());

        historique.setDuree(LocalTime.parse(produit.getDuree().toString()));
        historique.setUtilisateuridvendeur(produit.getId());
        historique.setDateencheriser(Date.valueOf(dt.toString()));
        System.out.print(produitid + "===TESTYUIOP" + prix);
        historique.setProduitid(produitid);
        // historique.set

        historique.setUtilisateuridacheteur(id);
        EtatSolde solde = null;
        try {
            solde = rep.FaireEncherir(historique);
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        ArrayList<EtatSolde> etatSolde = new ArrayList<>();
        etatSolde.add(solde);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(etatSolde);
        __data.add(data);
        return __data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/HistoriqueUtilisateurs/{iduser}", method = RequestMethod.GET, produces = "application/json")

    public ArrayList<Data> getHistoriqueUtildateurs(@PathVariable int iduser) {
        ArrayList<Data> _data = new ArrayList<>();
        try {
            ArrayList<HistoriqueUtilisateur> histoUtil = HistoREP.getHistoriqueUsers(iduser);
            data.setData(histoUtil);
            _data.add(data);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return _data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/encheresPropres/{iduser}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getHistoriquePropre(@PathVariable(value = "iduser") int id) {
        ArrayList<Data> _data = new ArrayList<Data>();
        try {
            ArrayList<HistoriqueUtilisateur> histo = HistoREP.getPropreHistorique(id);
            System.out.print(histo.size());
            data.setData(histo);
            _data.add(data);
        } catch (Exception e) {
            e.printStackTrace();
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
            // TODO: handle exception
        }
        return _data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/encheresNormes/{iduser}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getHistoriqueNorme(@PathVariable(value = "iduser") int id) {
        ArrayList<Data> _data = new ArrayList<Data>();
        try {
            ArrayList<HistoriqueUtilisateur> histo = HistoREP.getHistoriqueNormale(id);
            data.setData(histo);
            _data.add(data);
        } catch (Exception e) {
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
            // TODO: handle exception
        }
        return _data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/photos/{idproduit}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Data> getPhotos(@PathVariable(value = "idproduit") int idproduit) {
        ArrayList<Data> _data = new ArrayList<>();
        try {
            ArrayList<Photo> photo = ProdREP.getPhoto(idproduit);
            data.setData(photo);
            _data.add(data);
        } catch (Exception e) {
            // TODO: handle exception
            status = 500;
            message = "Une erreur s'est produite : " + e;
            Erreur __error = new Erreur(status, message);
            error.add(__error);
            data.setData(error);
        }
        return _data;
    }
    // @RequestMapping(value = "/Encheres", method = RequestMethod.POST, produces = "application/json")
    // @ResponseBody
    // @CrossOrigin
    // public ArrayList<Data> CreateEnchere(@RequestBody String body,
    //         HttpServletRequest request) {
    //     try {
    //         // Token token = new Token().ToToken(tok);
    //         ArrayList<Data> __data = new ArrayList<>();
    //         Produit produit = new Produit();
    //         produit.setNom(request.getParameter("nom"));
    //         produit.setUtilisateurid(Integer.parseInt(request.getParameter("idUtilisateur")));
    //         produit.setCategorieid(Integer.parseInt(request.getParameter("categorieid")));
    //         produit.setDateencheriser(LocalDateTime.now());
    //         produit.setDuree(LocalTime.parse(request.getParameter("duree")));
    //         produit.setPrix(Double.parseDouble(request.getParameter("prix")));
    //         // produit.setDateencheriser(new);
    //         int idprod = ProdREP.InsertProduit(produit);
    //         Photo[] sary = photo.ToPhoto(idprod, body);
    //         produit.setPhoto(sary);
    //         ArrayList<Produit> ReturnProduit = new ArrayList<>();
    //         ReturnProduit.add(produit);
    //         data.setData(ReturnProduit);
    //         __data.add(data);
    //         return __data;
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    // @RequestMapping(value = "/historiques", method = RequestMethod.GET, produces = "application/json")
    // @ResponseBody
    // public List<HistoriqueUtilisateur> getHistoriques() {
    //     return HistoREP.getHistoriqueAll();
    // }

}
