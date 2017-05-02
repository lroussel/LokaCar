package fr.eni.lokacar.ui.menu.freerent.rent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.eni.lokacar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RentFragment extends Fragment {


    public RentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rent, container, false);
    }

}
