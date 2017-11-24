package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterPantallaPrincipalPeliculas;

/**
 * A simple {@link Fragment} subclass.
 */
public class PantallaPrincipalFragmentPeliculas extends Fragment implements AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula {

    private List<Pelicula> listaDePeliculasEstreno;
    private List<Pelicula> listaDeTopMovies;
    private List<Pelicula> listaDePeliculasProximamente;
    private EscuchadorDePelicula escuchadorDePelicula;
    private AdapterPantallaPrincipalPeliculas adapterDePeliculasTopMovies;
    private AdapterPantallaPrincipalPeliculas adapterDePeliculasEstreno;
    private AdapterPantallaPrincipalPeliculas adapterDePeliculasProximamente;

    public PantallaPrincipalFragmentPeliculas() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePelicula = (PantallaPrincipalFragmentPeliculas.EscuchadorDePelicula) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantalla_principal_fragment_peliculas, container, false);

        //TextView textView = view.findViewById(R.id.textViewPalabraGeneros);
        RecyclerView recyclerViewDeTopMovies = view.findViewById(R.id.recyclerViewDeTopMovies);
        listaDeTopMovies = new ArrayList<>();

        recyclerViewDeTopMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterDePeliculasTopMovies = new AdapterPantallaPrincipalPeliculas(listaDeTopMovies, getContext(), this);
        recyclerViewDeTopMovies.setAdapter(adapterDePeliculasTopMovies);
        cargarTopPelis();


        RecyclerView recyclerViewPeliculasEstreno = view.findViewById(R.id.recyclerViewPeliculasEstreno);
        listaDePeliculasEstreno = new ArrayList<>();


        recyclerViewPeliculasEstreno.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterDePeliculasEstreno = new AdapterPantallaPrincipalPeliculas(listaDePeliculasEstreno, getContext(), this);
        recyclerViewPeliculasEstreno.setAdapter(adapterDePeliculasEstreno);
        cargarPelisEstreno();


        RecyclerView recyclerViewPeliculasProximamente = view.findViewById(R.id.recyclerViewPeliculasProximamente);
        listaDePeliculasProximamente = new ArrayList<>();


        recyclerViewPeliculasProximamente.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterDePeliculasProximamente = new AdapterPantallaPrincipalPeliculas(listaDePeliculasProximamente, getContext(), this);
        recyclerViewPeliculasProximamente.setAdapter(adapterDePeliculasProximamente);
        cargarPelisProximamnte();


        return view;

    }

    private void cargarPelisEstreno() {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePeliculasEstreno.clear();
                listaDePeliculasEstreno.addAll(resultado);
                adapterDePeliculasEstreno.notifyDataSetChanged();
            }
        };

        peliculasController.getMoviesPlayingNowList(escuchadorDeLaVista, getContext());

    }

    private void cargarPelisProximamnte() {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePeliculasProximamente.clear();
                listaDePeliculasProximamente.addAll(resultado);
                adapterDePeliculasProximamente.notifyDataSetChanged();
            }
        };

        peliculasController.getUpcomingMovies(escuchadorDeLaVista, getContext());

    }

    private void cargarTopPelis() {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDeTopMovies.clear();
                listaDeTopMovies.addAll(resultado);
                adapterDePeliculasTopMovies.notifyDataSetChanged();
            }
        };

        peliculasController.getUpcomingMovies(escuchadorDeLaVista, getContext());

    }


    @Override
    public void seleccionaronA(Pelicula unaPelicula) {
        escuchadorDePelicula.seleccionaronPelicula(unaPelicula);
    }

    public interface EscuchadorDePelicula {
        public void seleccionaronPelicula(Pelicula unaPelicula);
    }
}