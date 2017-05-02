package fr.eni.lokacar.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.eni.lokacar.MenuPrincipal;
import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.menu.MenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connexion(View view) {
        Intent it = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(it);
    }
}
