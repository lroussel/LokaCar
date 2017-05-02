package fr.eni.lokacar.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Client {
    private int id;
    private String nom;
    private String telephone;

    private List<Location> Locations = new ArrayList<>();

    public Client(int id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
