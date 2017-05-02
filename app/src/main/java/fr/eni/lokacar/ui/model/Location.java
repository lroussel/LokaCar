package fr.eni.lokacar.ui.model;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Location {

    private int Id;
    private String DateDebut;
    private String DateFin;

    private Vehicule Vehicule;
    private Client Client;

    public Location(int id, String dateDebut, String dateFin, Vehicule vehicule, Client client) {
        Id = id;
        DateDebut = dateDebut;
        DateFin = dateFin;
        Vehicule = vehicule;
        Client = client;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String dateDebut) {
        DateDebut = dateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }

    public Vehicule getVehicule() {
        return Vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        Vehicule = vehicule;
    }

    public Client getClient() {
        return Client;
    }

    public void setClient(Client client) {
        Client = client;
    }
}
