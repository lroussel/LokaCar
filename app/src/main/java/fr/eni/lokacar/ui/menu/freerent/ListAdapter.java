package fr.eni.lokacar.ui.menu.freerent;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

import fr.eni.lokacar.R;
import fr.eni.lokacar.ui.model.Vehicule;
import fr.eni.lokacar.ui.utils.CircleTransform;

/**
 * Created by lroussel2015 on 03/05/2017.
 */

public class ListAdapter extends ArrayAdapter<Vehicule> {

    private LayoutInflater inflater;
    private int vehiculeId;

    public ListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Vehicule> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        vehiculeId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(vehiculeId, null);

            viewHolder = new ViewHolder();
            viewHolder.tvMarqueModele = (TextView) convertView.findViewById(R.id.textView_MarqueModele);
            viewHolder.tvIMAT = (TextView) convertView.findViewById(R.id.textView_IMAT);
            viewHolder.tvPrix = (TextView) convertView.findViewById(R.id.textView_prix);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.item_icon);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Vehicule item = getItem(position);

        viewHolder.tvMarqueModele.setText(item.getMarque() + " - " + item.getModele());
        viewHolder.tvIMAT.setText(item.getImmatriculation());
        viewHolder.tvPrix.setText(item.getPrix());
        Picasso.with(getContext()).load(item.getImageURL()).transform(new CircleTransform()).into(viewHolder.ivPhoto);



        return convertView;
    }

    class ViewHolder{
        TextView tvMarqueModele, tvIMAT, tvPrix;
        ImageView ivPhoto;
    }
}
