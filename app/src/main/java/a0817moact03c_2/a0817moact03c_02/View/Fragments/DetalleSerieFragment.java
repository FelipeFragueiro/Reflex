package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSerieFragment extends Fragment {


    public DetalleSerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_detalle_serie, container, false);
        Bundle aBundle = getArguments();

        String nombreSerie = aBundle.getString("nombre_serie");
        String generoSerie = aBundle.getString("genero_serie");
        String descripcionSerie = aBundle.getString("descripcion_serie");
        int imagenSerie = aBundle.getInt("imagen_serie");

        TextView textViewNombreSerie = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaSerieDetalle);
        ImageView unImageViewPelicula = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaSerieDetalle);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGeneroDeLaSerieDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaSerieDetalle);

        textViewNombreSerie.setText(nombreSerie);
        unImageViewPelicula.setImageResource(imagenSerie);
        unTextViewDelGenero.setText(generoSerie);
        unTextViewDeLaDescripcion.setText(descripcionSerie);

        return fragmentView;
    }

}
