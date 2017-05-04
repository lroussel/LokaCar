package fr.eni.lokacar.ui.menu.freerent.rent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.Network;
import fr.eni.lokacar.ui.menu.freerent.ListAdapter;
import fr.eni.lokacar.ui.model.Vehicule;
import fr.eni.lokacar.ui.utils.Constant;
import fr.eni.lokacar.ui.utils.Preference;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentFragment extends Fragment {

    private ListView lvVehiculeLoue  ;
    private List<Vehicule> vehicules;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public RentFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RentFragment newInstance(int sectionNumber) {
        RentFragment fragment = new RentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rent, container, false);

        lvVehiculeLoue = (ListView) rootView.findViewById(R.id.listView_Vehicule_Loue);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String agence = Preference.getAgence(getContext());
        String url = String.format(Constant.URL_GET_VEHICULES, agence, true);

        if(Network.isNetworkAvailable(getContext())){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();

                            response = response.replace("\\", "");
                            int length = response.length();
                            response = response.substring(1, length - 1);

                            vehicules = new ArrayList<>();

                            try {
                                vehicules = gson.fromJson(response, new TypeToken<List<Vehicule>>() {
                                }.getType());
                            }catch (JsonSyntaxException jse){
                                jse.printStackTrace();
                            }

                            lvVehiculeLoue.setAdapter(new ListAdapter(
                                    getContext(),
                                    R.layout.adapter_liste_vehicule,
                                    vehicules
                            ));

                            lvVehiculeLoue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent it = new Intent(getContext(), InfoRentActivity.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Constant.INTENT_VEHICULE, vehicules.get(i));

                                    it.putExtras(bundle);

                                    startActivity(it);

                                }
                            });
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "erreur inconnue", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        }
        // Inflate the layout for this fragment
        return rootView;
    }

}
