package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enchere.enchere.model.EtatSolde;
import com.enchere.enchere.model.HistoriqueUtilisateur;
import com.enchere.enchere.model.Photo;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.model.Utilisateur;

@Repository
public class ProduitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    HistoriqueRepository histoREP;

    @Autowired
    UtilisateurRepository UtilREP;

    public void UpdateProduit(double montant, int produitid) {
        String sql = "update produit set prix= " + montant + ", dateencheriser=now() where id=" + produitid;
        jdbcTemplate.update(sql);
    }

    public boolean TestClient(int idutilisateur, int id) throws Exception {//// TESTER === TRUE
        String sql = "SELECT * FROM Produit where utilisateurid=? and id=?";
        ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), idutilisateur, id);
        if (tab.size() == 0) {
            return true;
        } else {
            throw new Exception("C'est votre propre enchere");
        }
    }

    public boolean TESTMONTANT(int id, double montant) throws Exception {//// TESTER === TRUE
        String sql = "SELECT * FROM Produit where prix>=? and id=? and etat=0";
        ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), montant, id);
        System.out.println("SIZE=======>" + tab.size());
        if (tab.size() == 0) {
            System.out.println("SIZE=======>" + tab.size());
            return true;
        } else {
            throw new Exception("MOntant inferieur au dernier montant");
        }
    }

    public EtatSolde TestBlocked(int etatActuelle, int produitid, int utilisateurid, double montant) throws Exception {//// TESTER
        //// ==
        //// TRUE
        EtatSolde etatsolde = new EtatSolde();
        ArrayList<HistoriqueUtilisateur> histo = histoREP.getHistoActus(utilisateurid);
        double soldeMouv = 0;
        Utilisateur utili = UtilREP.getOneUtilisateur(utilisateurid);
        System.out.print("==>" + histo.size());
        for (int i = 0; i < histo.size(); i++) {
            soldeMouv += histo.get(i).getPrix();
        }
        System.out.print(utili.getSolde() + "===Solde===soldeMouv" + soldeMouv + "IDNI==>" + histo.size());
        double soldeUnB = utili.getSolde() - (soldeMouv + montant);
        etatsolde.setSoldeBlocked(soldeMouv + montant);
        etatsolde.setSolde(utili.getSolde());
        etatsolde.setSoldeUnBlocked(soldeUnB);
        if (montant < soldeUnB) {
            etatsolde.setSituation("Succes");
        } else {
            etatsolde.setSituation("Solde Insuffisant==>" + histo.size());
            throw new Exception("Solde Insuffisant : votre solde est " + utili.getSolde());
        }

        return etatsolde;
    }

    public EtatSolde FaireEncherir(HistoriqueUtilisateur utilHisto)
            throws Exception {
        EtatSolde etatsolde = new EtatSolde();
        try {
            Date date = new Date();
            TestClient(utilHisto.getUtilisateuridacheteur(), utilHisto.getProduitid());
            System.out.println(utilHisto.getPrix() + "====> " + utilHisto.getProduitid());
            TESTMONTANT(utilHisto.getProduitid(), utilHisto.getPrix());
            etatsolde = TestBlocked(1, utilHisto.getProduitid(), utilHisto.getUtilisateuridacheteur(),
                    utilHisto.getPrix());
            UpdateProduit(utilHisto.getPrix(), utilHisto.getProduitid());
            histoREP.UpdateHisto(utilHisto.getProduitid());
            utilHisto.setEtatActuelle(1);
            histoREP.InsertHistorique(utilHisto);

        } catch (Exception ex) {
            etatsolde.setSituation(ex.getMessage());
            return etatsolde;
            // throw ex;
        }

        return etatsolde;
    }

    public Produit getById(int idprod) {
        String sql2 = "select * from produit where id=?";
        ArrayList<Produit> produit = (ArrayList<Produit>) jdbcTemplate.query(sql2,
                new BeanPropertyRowMapper<Produit>(Produit.class), idprod);

        if (produit != null) {
            return produit.get(0);
        }
        return new Produit();
    }

    public ArrayList<Photo> getPhoto() {
        String sql = "SELECT * FROM Photo";
        ArrayList<Photo> tab = (ArrayList<Photo>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Photo>(Photo.class));
        return tab;
    }

    public ArrayList<Photo> getPhoto(int id) {
        String sql = "select * from photo where idproduit=?";
        ArrayList<Photo> tab = (ArrayList<Photo>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Photo>(Photo.class));
        return tab;

    }

    public int InsertProduit(Produit prod) {
        String sql = "insert into produit(nom,prix,utilisateurid,categorieid,duree,dateEncheriser)values(?,?,?,?,?,CURRENT_TIMESTAMP)";
        try {
            jdbcTemplate.update(sql, prod.getNom(), prod.getPrix(), prod.getUtilisateurid(), prod.getCategorieid(),
                    prod.getDuree());
            String __sql = "SELECT * FROM Produit WHERE nom=? AND prix=? AND utilisateurid=? AND duree=? LIMIT 1";
            ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(__sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), prod.getNom(), prod.getPrix(), prod.getUtilisateurid(),
                    prod.getDuree());
            int idprod = 1;
            if(tab.size() != 0){
                idprod = tab.get(0);   
            }
            for (int i = 0; i < tab.size(); i++) {
                idprod = tab.get(i).getId();
            }
            return 1;
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }


}
