package com.enchere.enchere.DAO;

import java.util.ArrayList;

import com.enchere.enchere.model.Vehicule;

public interface VehiculeDAO {
    ArrayList<Vehicule> SelectAll();

    ArrayList<Vehicule> SelectByIdVehicule(int id);

    void Save(Vehicule vehicule);

    void Update(Vehicule vehicule);

    void Delete(Vehicule vehicule);
}
