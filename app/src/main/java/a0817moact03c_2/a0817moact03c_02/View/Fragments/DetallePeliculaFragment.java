package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdaptadorDePeliculaRecycler.EscuchadorDePeliculas {
    private List<Pelicula> listaDePeliculas;
    private AdaptadorDePeliculaRecycler adaptadorDePeliculaRecycler;
    private EscuchadorDePeliculasInterface escuchadorDePeliculasInterface;


    public DetallePeliculaFragment() {
        // Required em
        // pty public constructor
    }

    public static DetallePeliculaFragment dameUnDetallePeliculaFragment(Pelicula unaPelicula) {
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        Bundle args = new Bundle();
        args.putString("nombre_pelicula2",unaPelicula.getNombre());
        args.putInt("posicion_pelicula2",unaPelicula.getPosicion());
        args.putInt("id_pelicula2",unaPelicula.getId());
        args.putString("genre_pelicula2",unaPelicula.getGenre());
        args.putString("overview_pelicula2",unaPelicula.getOverview());
        args.putString("poster_path_pelicula2",unaPelicula.getPoster_path());
        args.putString("release_date_pelicula2",unaPelicula.getRelease_date());

        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePeliculasInterface = (DetallePeliculaFragment.EscuchadorDePeliculasInterface) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);
        Bundle aBundle = getArguments();
        String unTitulo = aBundle.getString("nombre_pelicula");
        final String unaImagen = aBundle.getString("poster_path_pelicula");
        String unaDescripcion = aBundle.getString("overview_pelicula");
        String unGenero = aBundle.getString("genre_pelicula");


        TextView textViewNombrePelicula = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaSerieDetalle);
        ImageView unImageViewPelicula = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaSerieDetalle);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGeneroDeLaSerieDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaSerieDetalle);
        textViewNombrePelicula.setText(unTitulo);
        Glide.with(getContext()).load(unaImagen).into(unImageViewPelicula);
        unTextViewDelGenero.setText(unGenero);
        unTextViewDeLaDescripcion.setText(unaDescripcion);

        unImageViewPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escuchadorDePeliculasInterface.seleccionaronImagen(unaImagen);
            }
        });


        listaDePeliculas = new ArrayList<>();

        //1) Buscar el recycler View
        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDePeliculasSugeridas);

        //2) Crear el adaptador
        adaptadorDePeliculaRecycler = new AdaptadorDePeliculaRecycler(listaDePeliculas, getContext(), this);

        //Como quiero que muestre las celdas
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        // recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));


        //3) Setearle el adaptador al recycler view
        //listViewPersonajes.setAdapter(unAdaptadorDePersonaje);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>>escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePeliculas.clear();
                listaDePeliculas = resultado;
                adaptadorDePeliculaRecycler.notifyDataSetChanged();

                //escuchadorDePeliculasInterface.seleccionaronPelicula();
            }
        };

        peliculasController.getMoviesList(escuchadorDeLaVista,getActivity());

        return fragmentView;
    }




    public void seleccionaronA(Pelicula unaPeli) {
        escuchadorDePeliculasInterface.seleccionaronPelicula(unaPeli);
    }

    public interface EscuchadorDePeliculasInterface {
        public void seleccionaronPelicula(Pelicula unaPelicula);
        public void seleccionaronImagen(String unInt);
    }
}
