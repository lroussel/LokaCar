package fr.eni.lokacar.ui.utils;

/**
 * Created by lroussel2015 on 03/05/2017.
 */

public class Constant {
    /**
     * Constante Globale
     */
    public static final String PREF_NOM = "PREF_NOM";
    public static final String PREF_EMAIL = "PREF_EMAIL";
    public static final String PREF_PASSWORD = "PREF_PASSWORD";
    public static final String PREF_AGENCE = "PREF_AGENCE";
    public static final String INTENT_VEHICULE = "INTENT_VEHICULE";
    public static final String INTENT_VEHICULE_ASSOC = "INTENT_VEHICULE_ASSOC";
    public static final String PREF_RAPPEL = "PREF_RAPPEL";

    /**
     * Requêtes URL pour WebService
     */
    public static final String URL_GET_GERANT = "http://10.4.140.19/json/srv/gerant_%s";
    public static final String URL_GET_VEHICULES = "http://10.4.140.19/json/srv/vehicules_%s&%s";
    public static final String URL_ADD_CAR = "http://10.4.140.19/json/srv/addVehicule";
    public static final String URL_ADD_LOC = "http://10.4.140.19/json/srv/addLocation";
    public static final String URL_DELETE_CAR ="http://10.4.140.19/json/srv/deletevehicule_%s" ;
    public static final String URL_DISSOCIATE = "http://10.4.140.19/json/srv/deletelocation_%s";
    public static final String SMS_CLIENT = "M. %s, Vous venez de louer la voiture %s (Immatriculation : %s) du %s au %s. Bonne route !";
}
