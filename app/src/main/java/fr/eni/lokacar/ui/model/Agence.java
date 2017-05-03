package fr.eni.lokacar.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Agence {

    private int Id;
    private String Nom;

    private List<Vehicule> VehiculeEntity = new ArrayList<>();
    private Gerant GerantEntity;

    public Agence(int id, String nom, List<Vehicule> vehicules, Gerant gerant) {
        Id = id;
        Nom = nom;
        VehiculeEntity = vehicules;
        GerantEntity = gerant;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public List<Vehicule> getVehicules() {
        return VehiculeEntity;
    }

    public void setVehicules(List<Vehicule> vehicules) {
        VehiculeEntity = vehicules;
    }

    public fr.eni.lokacar.ui.model.Gerant getGerant() {
        return GerantEntity;
    }

    public void setGerant(fr.eni.lokacar.ui.model.Gerant gerant) {
        GerantEntity = gerant;
    }
}
