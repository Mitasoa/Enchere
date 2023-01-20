package com.enchere.enchere.DAO;

import com.enchere.enchere.model.HistoriqueUtilisateur;

import java.util.ArrayList;

public interface HistoriqueUtilisateurDAO {
    ArrayList<HistoriqueUtilisateur> SelectHistoriqueById(int idUtilisateur);
}
