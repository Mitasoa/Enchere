package com.enchere.enchere.model;

public class EtatSolde {
    double solde;
    double soldeBlocked;
    double soldeUnBlocked;
    String situation;

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSituation() {
        return situation;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setSoldeBlocked(double soldeBlocked) {
        this.soldeBlocked = soldeBlocked;
    }

    public void setSoldeUnBlocked(double soldeUnBlocked) {
        this.soldeUnBlocked = soldeUnBlocked;
    }

    public double getSolde() {
        return solde;
    }

    public double getSoldeBlocked() {
        return soldeBlocked;
    }

    public double getSoldeUnBlocked() {
        return soldeUnBlocked;
    }
}
