package fr.eni.lokacar.ui.menu.freerent.free;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.AssociateClientActivity;
import fr.eni.lokacar.ui.Network;
import fr.eni.lokacar.ui.model.Vehicule;
import fr.eni.lokacar.ui.utils.Constant;

public class InfoFreeActivity extends AppCompatActivity {

    private TextView tvMarque, tvModele, tvIMAT, tvPrix;
    private ImageView ivPhoto;
    private AppBarLayout ablPhoto;
    private FloatingActionButton update, delete;
    private Vehicule vehicule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_free);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMarque = (TextView) findViewById(R.id.textView_Marque);
        tvModele = (TextView) findViewById(R.id.textView_Modele);
        tvIMAT = (TextView) findViewById(R.id.textView_IMAT);
        tvPrix = (TextView) findViewById(R.id.textView_prix);

        ablPhoto = (AppBarLayout) findViewById(R.id.app_bar);

        update = (FloatingActionButton) findViewById(R.id.update);
        delete = (FloatingActionButton) findViewById(R.id.delete);

        if(getIntent().getExtras() != null){

            vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);

            Picasso.with(InfoFreeActivity.this).load(vehicule.getImageURL()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    ablPhoto.setBackground(new BitmapDrawable(InfoFreeActivity.this.getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Log.d("TAG", "FAILED");
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Log.d("TAG", "Prepare load");
                }
            });

            getSupportActionBar().setTitle(vehicule.getMarque());

            tvModele.setText(vehicule.getModele());
            tvIMAT.setText(vehicule.getImmatriculation());
            tvPrix.setText(vehicule.getPrix());

        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Modification d'un véhicule
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = String.format(Constant.URL_DELETE_CAR, String.valueOf(vehicule.getId()));
                if(Network.isNetworkAvailable(InfoFreeActivity.this)) {
                    
                }
            }



        });
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

    public void associateClient(View view) {
        Intent it = new Intent(InfoFreeActivity.this, AssociateClientActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_VEHICULE_ASSOC, vehicule);

        it.putExtras(bundle);

        startActivity(it);
    }
}
