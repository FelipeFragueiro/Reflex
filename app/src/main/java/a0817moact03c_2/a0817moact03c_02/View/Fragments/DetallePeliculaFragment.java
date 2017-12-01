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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeActoresRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterPantallaPrincipalPeliculas;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula, AdaptadorDeActoresRecycler.EscuchadorDeActores {
    private List<Pelicula> listaDePeliculasSimilares;
    private List<Actores>listaDeActores;
    private AdapterPantallaPrincipalPeliculas adaptadorDePeliculaRecycler;
    private AdaptadorDeActoresRecycler adaptadorDeActoresRecycler;
    private EscuchadorDePelicula escuchadorDePeliculasInterface;
    private AdaptadorDeActoresRecycler.EscuchadorDeActores escuchadorDeActores;



    public DetallePeliculaFragment() {
        // Required em
        // pty public constructor
    }

    public static DetallePeliculaFragment dameUnDetallePeliculaFragment(Pelicula unaPelicula) {
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        Bundle args = new Bundle();
        args.putString("nombre_pelicula2",unaPelicula.getNombre());
        args.putInt("posicion_pelicula2",unaPelicula.getPosicion());
        args.putString("id_pelicula2",unaPelicula.getId());
        args.putString("genre_pelicula2",unaPelicula.getGenre());
        args.putString("overview_pelicula2",unaPelicula.getOverview());
        args.putString("poster_path_pelicula2",unaPelicula.getPoster_path());
        args.putString("release_date_pelicula2",unaPelicula.getRelease_date());

        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePeliculasInterface = (DetallePeliculaFragment.EscuchadorDePelicula) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);
        Bundle aBundle = getArguments();
        String unId = aBundle.getString("id_pelicula");
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




        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDePeliculasSugeridas);
        listaDePeliculasSimilares = new ArrayList<>();
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDePeliculaRecycler = new AdapterPantallaPrincipalPeliculas(listaDePeliculasSimilares, getContext(), (AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula) this);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        cargarPelisSimilares(unId);


        RecyclerView recyclerViewDeActores = (RecyclerView)fragmentView.findViewById(R.id.recyclerViewDelRepartoPelicula);
        listaDeActores = new ArrayList<>();
        recyclerViewDeActores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDeActoresRecycler = new AdaptadorDeActoresRecycler(listaDeActores,getContext(),escuchadorDeActores);
        recyclerViewDeActores.setAdapter(adaptadorDeActoresRecycler);
        return fragmentView;
    }

    private void cargarPelisSimilares(final String unId) {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePeliculasSimilares.clear();
                listaDePeliculasSimilares.addAll(resultado);
                adaptadorDePeliculaRecycler.notifyDataSetChanged();
                cargarActoresRelacionados(unId);
            }
        };

        peliculasController.getMoviesSimilarList(escuchadorDeLaVista, getContext(),unId);

    }

    private void cargarActoresRelacionados(String unId) {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Actores>> escuchadorDeLaVista = new ResultListener<List<Actores>>() {
            @Override
            public void finish(List<Actores> resultado) {
                listaDeActores.clear();
                listaDeActores.addAll(resultado);
                adaptadorDeActoresRecycler.setListaDeActores(listaDeActores);
                adaptadorDeActoresRecycler.notifyDataSetChanged();
            }
        };

        peliculasController.getMovieCredits(escuchadorDeLaVista, getContext(),unId);

    }




    public void seleccionaronA(Pelicula unaPeli) {
        escuchadorDePeliculasInterface.seleccionaronPelicula(unaPeli);
    }

    @Override
    public void seleccionaronA(Actores unActor) {

    }

    public interface EscuchadorDePelicula {
        public void seleccionaronPelicula(Pelicula unaPelicula);
    }

}
