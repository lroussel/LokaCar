package fr.eni.lokacar.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import fr.eni.lokacar.ui.utils.Constant;

/**
 * Created by lroussel2015 on 03/05/2017.
 */

public class Preference {

    private static SharedPreferences get(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getNom(Context context){
        return get(context).getString(Constant.PREF_NOM, "");
    }

    public static void setNom(Context context, String nom){
        get(context).edit().putString(Constant.PREF_NOM, nom).commit();
    }

    public static String getEmail(Context context){
        return get(context).getString(Constant.PREF_EMAIL, "");
    }

    public static void setEmail(Context context, String email){
        get(context).edit().putString(Constant.PREF_EMAIL, email).commit();
    }

    public static String getPassword(Context context){
        return get(context).getString(Constant.PREF_PASSWORD, "");
    }

    public static void setPassword(Context context, String password){
        get(context).edit().putString(Constant.PREF_PASSWORD, password).commit();
    }

    public static String getAgence(Context context){
        return get(context).getString(Constant.PREF_AGENCE, "");
    }

    public static void setAgence(Context context, String agence){
        get(context).edit().putString(Constant.PREF_AGENCE, agence).commit();
    }
}
