package fr.eni.lokacar.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.menu.MenuActivity;
import fr.eni.lokacar.ui.model.Vehicule;
import fr.eni.lokacar.ui.utils.Constant;

public class AssociateClientActivity extends AppCompatActivity {

    private EditText etDateDebut, etDateFin, etNom, etTelephone;

    private Vehicule vehicule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate_client);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDateDebut = (EditText) findViewById(R.id.editText_DateDebut);
        etDateFin = (EditText) findViewById(R.id.editText_DateFin);
        etNom = (EditText) findViewById(R.id.editText_NomClient);
        etTelephone = (EditText) findViewById(R.id.editText_Telephone);

        if(getIntent().getExtras() != null){
            vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE_ASSOC);

            getSupportActionBar().setTitle(vehicule.getMarque() + " - " + vehicule.getModele());

        }
    }

    public void seeCalendar(View view) {

        final Calendar calendar = Calendar.getInstance();

        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear) + "/" + String.valueOf(year);
                if(etDateDebut.hasFocus()) {
                    etDateDebut.setText(date);
                }else if (etDateFin.hasFocus()){
                    etDateFin.setText(date);
                }
            }
        }, yy, mm, dd);

        datePicker.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void associateVehiculeClient(View view) {
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(AssociateClientActivity.this);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("vehicule", vehicule.getImmatriculation());
            jsonBody.put("client", etNom.getText().toString());
            jsonBody.put("telephone", etTelephone.getText().toString());
            jsonBody.put("debut", etDateDebut.getText().toString());
            jsonBody.put("fin", etDateFin.getText().toString());

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_ADD_LOC, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("200")){
                        Toast.makeText(AssociateClientActivity.this, "Enregistrement effectué", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(AssociateClientActivity.this, MenuActivity.class);
                        startActivity(it);
                        finish();
                    }else{
                        Toast.makeText(AssociateClientActivity.this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
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
                    try{
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
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
