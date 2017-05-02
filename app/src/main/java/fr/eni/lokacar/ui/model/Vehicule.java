package fr.eni.lokacar.ui.model;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Vehicule {

    private int Id;
    private String Marque;
    private String Modele;
    private String Immatriculation;
    private String ImageURL;

    private Agence Agence;
    private Location Location;

    public Vehicule(int id, String marque, String modele, String immatriculation,
                    String imageURL, Agence agence, Location location) {
        Id = id;
        Marque = marque;
        Modele = modele;
        Immatriculation = immatriculation;
        ImageURL = imageURL;
        Agence = agence;
        Location = location;
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


    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location location) {
        Location = location;
    }
}
