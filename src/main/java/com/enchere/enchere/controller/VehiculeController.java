package com.enchere.enchere.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enchere.enchere.model.Data;
import com.enchere.enchere.model.Vehicule;
import com.enchere.enchere.repository.VehiculeRepository;

@Controller
public class VehiculeController {

    @Autowired
    private VehiculeRepository rep;

    private Data data = new Data();

    // Select
    @RequestMapping(value = "/vehicules", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> getAllVehicule() {
        ArrayList<Vehicule> vehicule = rep.SelectAll();
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(vehicule);
        __data.add(data);
        return __data;
    }

    // Insert
    @RequestMapping(value = "/vehicule", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> insertVehicule(HttpServletRequest request) {
        Vehicule retour = new Vehicule();
        retour.setMarqueid(Integer.parseInt(request.getParameter("marqueid")));
        retour.setModelid(Integer.parseInt(request.getParameter("modelid")));
        retour.setSerieid(Integer.parseInt(request.getParameter("serieid")));
        retour.setImmatriculation(request.getParameter("immatriculation"));
        rep.Save(retour);
        ArrayList<Vehicule> vehicule = new ArrayList<>();
        vehicule.add(retour);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(vehicule);
        __data.add(data);
        return __data;
    }

    // Update
    @RequestMapping(value = "/vehicule/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> updateVehicule(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        Vehicule retour = new Vehicule();
        retour = this.getVehiculeUnique(id);
        if (request.getParameter("marqueid") != null) {
            retour.setMarqueid(Integer.parseInt(request.getParameter("marqueid")));
        }
        if (request.getParameter("modelid") != null) {
            retour.setModelid(Integer.parseInt(request.getParameter("modelid")));
        }
        if (request.getParameter("serieid") != null) {
            retour.setSerieid(Integer.parseInt(request.getParameter("serieid")));
        }
        if (request.getParameter("immatriculation") != null) {
            retour.setImmatriculation(request.getParameter("immatriculation"));
        }
        rep.Update(retour);
        ArrayList<Vehicule> vehicule = new ArrayList<>();
        vehicule.add(retour);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(vehicule);
        __data.add(data);
        return __data;
    }

    // Delete
    @RequestMapping(value = "/vehicule/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> deleteVehicule(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        Vehicule retour = new Vehicule();
        retour = this.getVehiculeUnique(id);
        retour.setId(1);
        rep.Delete(retour);
        ArrayList<Vehicule> vehicule = new ArrayList<>();
        vehicule.add(retour);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(vehicule);
        __data.add(data);
        return __data;
    }

    // Select by id
    @RequestMapping(value = "/vehicules/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Data> getByIdVehicule(@PathVariable(value = "id") Integer id) {
        ArrayList<Vehicule> vehicule = rep.SelectByIdVehicule(id);
        ArrayList<Data> __data = new ArrayList<>();
        data.setData(vehicule);
        __data.add(data);
        return __data;
    }

    // Select un vehicule
    public Vehicule getVehiculeUnique(int id) {
        ArrayList<Vehicule> vehicule = rep.SelectByIdVehicule(id);
        if (vehicule.size() == 0) {
            return null;
        }
        Vehicule retour = (Vehicule) vehicule.get(0);
        return retour;
    }

}
