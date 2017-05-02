package fr.eni.lokacar.ui.model;

/**
 * Created by lroussel2015 on 02/05/2017.
 */

public class Gerant {

    private int Id;
    private String Nom;
    private String Email;
    private String Password;

    private Agence Agence;

    public Gerant(int id, String nom, String email, String password, Agence agence) {
        Id = id;
        Nom = nom;
        Email = email;
        Password = password;
        Agence = agence;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Agence getAgence() {
        return Agence;
    }

    public void setAgence(Agence agence) {
        Agence = agence;
    }
}
