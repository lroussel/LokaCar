package fr.eni.lokacar.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import fr.eni.lokacar.ui.menu.freerent.MenuActivity;
import fr.eni.lokacar.ui.utils.FastDialog;
import fr.eni.lokacar.ui.utils.Preference;
import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.Network;
import fr.eni.lokacar.ui.model.Gerant;
import fr.eni.lokacar.ui.utils.Constant;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private CheckBox cbRappel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.editText_mail);
        etPassword = (EditText) findViewById(R.id.editText_password);
        cbRappel = (CheckBox) findViewById(R.id.checkBox_rappel);

        if(Preference.getRappel(MainActivity.this).equals("true")){
            etEmail.setText(Preference.getEmail(MainActivity.this));
            etPassword.setText(Preference.getPassword(MainActivity.this));
            cbRappel.setChecked(true);
        }
    }

    public void connexion(View view) {

        if(etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            FastDialog.showDialog(MainActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez inscrire l'email et le mot de passe pour continuer");
        }else {

            // TODO : Recuperation des info du gérant connecté
            if (Network.isNetworkAvailable(MainActivity.this)) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = String.format(Constant.URL_GET_GERANT, etEmail.getText().toString());

                // Request a string response from the provided url
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                // TODO : Gérer le fait que l'email n'existe pas dans la BDD

                                Gson gson = new Gson();

                                response = response.replace('\\', ' ');
                                response = response.replace('"', ' ');

                                Gerant gerant = null;

                                try {
                                    gerant = gson.fromJson(response, Gerant.class);
                                }catch(JsonSyntaxException jse){
                                    jse.printStackTrace();
                                }catch(JsonParseException jpe){
                                    jpe.printStackTrace();
                                }

                                if (etPassword.getText().toString().equals(gerant.getPassword())) {
                                    Preference.setNom(MainActivity.this, gerant.getNom());
                                    Preference.setEmail(MainActivity.this, gerant.getEmail());
                                    Preference.setPassword(MainActivity.this, gerant.getPassword());
                                    Preference.setAgence(MainActivity.this, gerant.getAgence().getNom());
                                    if(cbRappel.isChecked()){
                                        Preference.setRappel(MainActivity.this, "true");
                                    }else{
                                        Preference.setRappel(MainActivity.this, "false");
                                    }

                                    Intent it = new Intent(MainActivity.this, MenuActivity.class);
                                    startActivity(it);
                                } else {
                                    Toast.makeText(MainActivity.this, "Le mot de passe n'est pas correct", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO : definir l'erreur selon l'erreur emise
                        Toast.makeText(MainActivity.this, "Erreur inconnue", Toast.LENGTH_SHORT).show();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        }

    }
}
