package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Controller.SeriesController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeSeries;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSerieFragment extends Fragment implements AdaptadorDeSeries.EscuchadorDeSerie{
    private List<Serie> listaDeSeries;
    private AdaptadorDeSeries adaptadorDeSeries;
    //private EscuchadorDeSeriesInterface escuchadorDeSeriesInterface;

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
        final String imagenSerie = aBundle.getString("imagen_serie");

      //  TextView textViewNombreSerie = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaSerieDetalle);
        ImageView unImageViewSerie = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaSerieDetalle);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGeneroDeLaSerieDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaSerieDetalle);

     //   textViewNombreSerie.setText(nombreSerie);
        unTextViewDelGenero.setText(generoSerie);
        unTextViewDeLaDescripcion.setText(descripcionSerie);
        Glide.with(getContext()).load(imagenSerie).into(unImageViewSerie);

        listaDeSeries = new ArrayList<>();
        //RecyclerView recyclerViewSeries = (RecyclerView)fragmentView.findViewById(R.id.rec)
        SeriesController peliculasController = new SeriesController();

        /*ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeries.clear();
                listaDeSeries = resultado;
                adaptadorDePeliculaRecycler.notifyDataSetChanged();

                //escuchadorDePeliculasInterface.seleccionaronPelicula();
            }
        };

        peliculasController.getMoviesList(escuchadorDeLaVista,getActivity());
*/

        return fragmentView;
    }

    @Override
    public void seleccionaronA(Serie unaSerie) {

    }
}
