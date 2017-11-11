package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Genero;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorGenerosDePelicula;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaGenerosDePeliculaFragment extends Fragment implements AdaptadorGenerosDePelicula.EscuchadorDeGeneros {
    private List<Genero>listaDeGeneros;
    private AdaptadorGenerosDePelicula adaptadorGenerosDePelicula;
    private EscuchadorDeGeneros escuchadorDeGeneros;

    public ListaGenerosDePeliculaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDeGeneros = (EscuchadorDeGeneros)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_generos, container, false);

        //TextView textView = view.findViewById(R.id.textViewPalabraGeneros);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGeneros);
        listaDeGeneros = new ArrayList<>();



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        AdaptadorGenerosDePelicula adaptadorGenerosDePelicula = new AdaptadorGenerosDePelicula(listaDeGeneros, getContext(), this);

        recyclerView.setAdapter(adaptadorGenerosDePelicula);

        listaDeGeneros.add(new Genero("Accion",R.drawable.accion));
        listaDeGeneros.add(new Genero("Animadas",R.drawable.animadas));
        listaDeGeneros.add(new Genero("Anime",R.drawable.anime));
        listaDeGeneros.add(new Genero("Aventura",R.drawable.aventura));
        listaDeGeneros.add(new Genero("Ciencia Ficcion",R.drawable.ciencia_ficcion));
        listaDeGeneros.add(new Genero("Comedia",R.drawable.comedia));
        listaDeGeneros.add(new Genero("Deporte",R.drawable.deportes));
        listaDeGeneros.add(new Genero("Documentales",R.drawable.documentales));
        listaDeGeneros.add(new Genero("Drama",R.drawable.drama));
        listaDeGeneros.add(new Genero("Infantil",R.drawable.infantil));
        listaDeGeneros.add(new Genero("Policiaca",R.drawable.policial));
        listaDeGeneros.add(new Genero("Romantico",R.drawable.romantica));
        listaDeGeneros.add(new Genero("Terror",R.drawable.terror));
        listaDeGeneros.add(new Genero("Thriller",R.drawable.thriller));
        listaDeGeneros.add(new Genero("Suspenso",R.drawable.suspenso));

        return view;

    }


    @Override
    public void seleccionaronA(Genero unGenero) {
      escuchadorDeGeneros.seleccionaronGenero(unGenero);

    }

    public interface EscuchadorDeGeneros{
        public void seleccionaronGenero(Genero unGenero);
    }
}
