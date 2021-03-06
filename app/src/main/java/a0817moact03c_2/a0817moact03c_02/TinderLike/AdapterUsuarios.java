package a0817moact03c_2.a0817moact03c_02.TinderLike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Usuario;
import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by Ines on 18/12/2017.
 */

public class AdapterUsuarios extends ArrayAdapter<Usuario>{
    Context context;

    AdapterUsuarios(Context context, int resourseID, List<Usuario> items){
        super(context,resourseID,items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Usuario usuario = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView pelicuaFavorita1 = (TextView)convertView.findViewById(R.id.textviewfavoritos1);
        TextView nombreTextView = (TextView)convertView.findViewById(R.id.nameTindertextview);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewTinder);


        nombreTextView.setText(usuario.getNombre());


        if(usuario.getPeliculaFavorita() != null) {


        pelicuaFavorita1.setText(usuario.getPeliculaFavorita().getTitle());


    }

      /* if(usuario.getPeliculaFavorita().get(1) != null) {
            textviewpeliculafavorita2.setText(usuario.getPeliculaFavorita().get(1).getTitle());
        }else {
            textviewpeliculafavorita2.setText("-");
        }*/

        /*if(usuario.getPeliculaFavorita().get(2) != null) {
            textViewPeliculaFavorita3.setText(usuario.getPeliculaFavorita().get(2).getTitle());
        }else {
            textViewPeliculaFavorita3.setText("-");
        }*/


        Glide.with(getContext()).load(usuario.getFoto()).into(imageView);
        return convertView;
    }
}
