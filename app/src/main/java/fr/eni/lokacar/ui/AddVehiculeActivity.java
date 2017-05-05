package fr.eni.lokacar.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.main.MainActivity;
import fr.eni.lokacar.ui.menu.freerent.MenuActivity;
import fr.eni.lokacar.ui.model.Gerant;
import fr.eni.lokacar.ui.model.Vehicule;
import fr.eni.lokacar.ui.utils.Constant;
import fr.eni.lokacar.ui.utils.FastDialog;
import fr.eni.lokacar.ui.utils.Preference;

public class AddVehiculeActivity extends AppCompatActivity {


    private EditText editTextMarque;
    private EditText editTextModele;
    private EditText editTextIMAT;
    private EditText editTextURLPhoto;
    private EditText editTextPrix;
    private boolean update;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicule);

        editTextMarque = (EditText) findViewById(R.id.editText_marque);
        editTextModele = (EditText) findViewById(R.id.editText_modele);
        editTextIMAT = (EditText) findViewById(R.id.editText_IMAT);
        editTextURLPhoto = (EditText) findViewById(R.id.editText_URL_photo);
        editTextPrix = (EditText) findViewById(R.id.editText_Prix);
        update = getIntent().getBooleanExtra("update", false);
        Vehicule car = (Vehicule) getIntent().getExtras().get("car");
        if (update){
            editTextMarque.setText(car.getMarque());
            editTextModele.setText(car.getModele());
            editTextIMAT.setText(car.getImmatriculation());
            editTextURLPhoto.setText(car.getImageURL());
            editTextPrix.setText(car.getPrix());

        }


    }

    public void saveCar(View view) {
        String URL;

        if(editTextMarque.getText().toString().equals("") || editTextModele.getText().toString().equals("")
                 || editTextIMAT.getText().toString().equals("") || editTextURLPhoto.getText().toString().equals("")){
            FastDialog.showDialog(AddVehiculeActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez inscrire l'email et le mot de passe pour continuer");
        }else {

            try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                if (update){
                    URL = Constant.URL_UPADTE_CAR;
                }else {
                    URL = Constant.URL_ADD_CAR;
                }
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("agence", Preference.getAgence(AddVehiculeActivity.this));
                jsonBody.put("marque", editTextMarque.getText().toString());
                jsonBody.put("modele", editTextModele.getText().toString());
                jsonBody.put("immat", editTextIMAT.getText().toString());
                jsonBody.put("photo", editTextURLPhoto.getText().toString());
                jsonBody.put("prix", editTextPrix.getText().toString());
                final String requestBody = jsonBody.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("200")){
                            Toast.makeText(AddVehiculeActivity.this, "Enregisrement effectué", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(AddVehiculeActivity.this, MenuActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(AddVehiculeActivity.this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
