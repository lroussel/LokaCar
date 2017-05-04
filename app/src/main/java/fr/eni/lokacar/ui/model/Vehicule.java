package fr.eni.lokacar.ui.model;

import java.io.Serializable;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Vehicule implements Serializable {

    private int Id;
    private String Marque;
    private String Modele;
    private String Immatriculation;
    private String ImageURL;
    private String Prix;

    private Agence Agence;
    private Location LocationEntity;

    public Vehicule(){

    }

    public Vehicule(int id, String marque, String modele, String immatriculation,
                    String imageURL, Agence agence, Location location, String prix) {
        Id = id;
        Marque = marque;
        Modele = modele;
        Immatriculation = immatriculation;
        ImageURL = imageURL;
        Agence = agence;
        LocationEntity = location;
        Prix = prix;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getModele() {
        return Modele;
    }

    public void setModele(String modele) {
        Modele = modele;
    }

    public String getImmatriculation() {
        return Immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        Immatriculation = immatriculation;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public Agence getAgence() {
        return Agence;
    }

    public void setAgence(Agence agence) {
        Agence = agence;
    }

    public Location getLocationEntity() {
        return LocationEntity;
    }

    public void setLocationEntity(Location locationEntity) {
        LocationEntity = locationEntity;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }
}
