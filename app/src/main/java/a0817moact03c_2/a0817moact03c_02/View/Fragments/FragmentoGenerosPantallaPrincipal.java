package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterGenerosDelNavigationView;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterPantallaPrincipalPeliculas;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoGenerosPantallaPrincipal extends Fragment implements AdapterGenerosDelNavigationView.EscuchadorDePeliculasDelGenero{
    private List<Pelicula>peliculaList;
    private String genero;
    private String nombreGenero;
    private AdapterGenerosDelNavigationView adapterGenerosDelNavigationView;
    private AdapterGenerosDelNavigationView.EscuchadorDePeliculasDelGenero escuchadorDePeliculasDelGenero;




    public FragmentoGenerosPantallaPrincipal(String genero,String nombreGenero) {
        this.genero = genero;
        this.nombreGenero = nombreGenero;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_generos_pantalla_principal, container, false);

        TextView nombeGenero = (TextView)view.findViewById(R.id.textViewGeneroPantallaPrincipal);
        nombeGenero.setText(nombreGenero);



        RecyclerView recyclerViewlistaDePeliculasDeXGenero = view.findViewById(R.id.RecyclerPeliculasDelGenero);
        peliculaList = new ArrayList<>();
        recyclerViewlistaDePeliculasDeXGenero.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapterGenerosDelNavigationView = new AdapterGenerosDelNavigationView(peliculaList,getContext(),escuchadorDePeliculasDelGenero);
        recyclerViewlistaDePeliculasDeXGenero.setAdapter(adapterGenerosDelNavigationView);
        cargarGenero();

        return view;
    }

    public void cargarGenero(){
        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>>listener = (new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                peliculaList.clear();
                peliculaList.addAll(resultado);
                adapterGenerosDelNavigationView.notifyDataSetChanged();
            }
        });
        peliculasController.getMoviesFromGenreList(listener,genero,getContext());
    }

    @Override
    public void seleccionaronA(Pelicula unaPelicula) {

    }
}