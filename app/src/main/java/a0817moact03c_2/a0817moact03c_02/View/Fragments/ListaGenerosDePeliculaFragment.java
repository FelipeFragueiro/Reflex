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
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
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

        listaDeGeneros.add(new Genero("Action",R.drawable.accion,28));
        listaDeGeneros.add(new Genero("Adventure",R.drawable.aventura,12));
        listaDeGeneros.add(new Genero("Animation",R.drawable.anime,16));
        listaDeGeneros.add(new Genero("Comedy",R.drawable.aventura,35));
        listaDeGeneros.add(new Genero("Crime",R.drawable.ciencia_ficcion,80));
        listaDeGeneros.add(new Genero("Documentary",R.drawable.comedia,99));
        listaDeGeneros.add(new Genero("Drama",R.drawable.deportes,18));
        listaDeGeneros.add(new Genero("Family",R.drawable.documentales,10751));
        listaDeGeneros.add(new Genero("Fantasy",R.drawable.drama,14));
        listaDeGeneros.add(new Genero("History",R.drawable.infantil,36));
        listaDeGeneros.add(new Genero("Horror",R.drawable.policial,27));
        listaDeGeneros.add(new Genero("Music",R.drawable.romantica,10402));
        listaDeGeneros.add(new Genero("Mystery",R.drawable.terror,9648));
        listaDeGeneros.add(new Genero("Romance",R.drawable.thriller,10749));
        listaDeGeneros.add(new Genero("Science Fiction",R.drawable.suspenso,878));
        listaDeGeneros.add(new Genero("TV Movie",R.drawable.suspenso,10770));
        listaDeGeneros.add(new Genero("Thriller",R.drawable.suspenso,53));
        listaDeGeneros.add(new Genero("War",R.drawable.suspenso,10752));
        listaDeGeneros.add(new Genero("Western",R.drawable.suspenso,37));

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
