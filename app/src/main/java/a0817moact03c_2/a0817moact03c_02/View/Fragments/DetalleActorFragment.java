package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleActorFragment extends Fragment {


    public DetalleActorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_actor, container, false);

        Bundle aBundle = getArguments();
        String proflePath = aBundle.getString("profile_path_actor");
        String name = aBundle.getString("nombre_actor");
        String alsoKnownAs  = aBundle.getString("also_known_as_actor");
        String character = aBundle.getString("character_actor");
        String birthday = aBundle.getString("birthday_actor");
        String biografia = aBundle.getString("biografia_actor");

        ImageView imageViewActor = (ImageView)view.findViewById(R.id.imageViewDelActorDetalle);
        TextView textViewNombreActor = (TextView)view.findViewById(R.id.textViewDelNombreDelActorDetalle);
        TextView textViewAlsoKnownAs = (TextView)view.findViewById(R.id.textViewTambienConocidoPorActorDetalle);
        TextView textViewCharacter = (TextView)view.findViewById(R.id.textViewDelPersonajeActorDetalle);
        TextView textViewBirthday = (TextView)view.findViewById(R.id.textViewDelCumpleañosDelActor);
        TextView textViewBiografia = (TextView)view.findViewById(R.id.textViewDeLaBiografiaDelActorDetalle);

        Glide.with(getContext()).load(proflePath).into(imageViewActor);
        textViewNombreActor.setText(name);
        textViewAlsoKnownAs.setText("Tambien conocido como: "+alsoKnownAs);
        textViewBirthday.setText(birthday);
        textViewCharacter.setText("Personaje: "+character);
        textViewBiografia.setText(biografia);


        return view;
    }

}
