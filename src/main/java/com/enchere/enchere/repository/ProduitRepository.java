package com.enchere.enchere.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enchere.enchere.model.EtatSolde;
import com.enchere.enchere.model.HistoriqueUtilisateur;
import com.enchere.enchere.model.Produit;
import com.enchere.enchere.model.Utilisateur;

public class ProduitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    HistoriqueRepository histoREP;

    @Autowired
    UtilisateurRepository UtilREP;

    public boolean TestClient(int idutilisateur, int id) throws Exception {
        String sql = "SELECT * FROM Produit where utilisateurid=? and id=?";
        ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), idutilisateur, id);
        if (tab.size() == 0) {
            return true;
        } else {
            throw new Exception("Failed Encheriser");
        }
    }

    public boolean TESTMONTANT(int id, double montant) throws Exception {
        String sql = "SELECT * FROM Produit where prix>? and id=?";
        ArrayList<Produit> tab = (ArrayList<Produit>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Produit>(Produit.class), montant, id);
        if (tab.size() == 0) {
            return true;
        } else {
            throw new Exception("Failed Encheriser");
        }
    }

    public EtatSolde TestBlocked(int etatActuelle, int produitid, int utilisateurid, double montant) throws Exception {
        EtatSolde etatsolde = new EtatSolde();
        List<HistoriqueUtilisateur> histo = histoREP.getHistorique(etatActuelle, produitid, utilisateurid);
        double soldeMouv = 0;
        Utilisateur utili = UtilREP.getOneUtilisateur(utilisateurid);
        for (int i = 0; i < histo.size(); i++) {
            soldeMouv += histo.get(i).getPrix();
        }
        double soldeUnB = utili.getSolde() - soldeMouv;
        etatsolde.setSoldeBlocked(soldeMouv);
        etatsolde.setSolde(utili.getSolde());
        etatsolde.setSoldeUnBlocked(soldeUnB);
        if (montant > soldeUnB) {
            etatsolde.setSituation("Solde Insuffisant");
            throw new Exception("Solde Insuffisant");
        }
        etatsolde.setSituation("Succes");
        return etatsolde;
    }

    public EtatSolde FaireEncherir(int etatActuelle, int produitid, int utilisateurid, double montant) {

    }

}
