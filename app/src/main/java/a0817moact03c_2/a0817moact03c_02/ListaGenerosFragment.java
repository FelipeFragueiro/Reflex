package a0817moact03c_2.a0817moact03c_02;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaGenerosFragment extends Fragment implements AdaptadorGeneros.EscuchadorDeGeneros {
    private List<Genero>listaDeGeneros;
    private AdaptadorGeneros adaptadorGeneros;
    private EscuchadorDeGeneros escuchadorDeGeneros;

    public ListaGenerosFragment() {
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

        TextView textView = view.findViewById(R.id.textViewPalabraGeneros);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGeneros);
        listaDeGeneros = new ArrayList<>();



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        AdaptadorGeneros adaptadorGeneros = new AdaptadorGeneros(listaDeGeneros, getContext(), this);

        recyclerView.setAdapter(adaptadorGeneros);

        listaDeGeneros.add(new Genero("Accion",R.drawable.accion));
        listaDeGeneros.add(new Genero("Animadas",R.drawable.animadas));
        listaDeGeneros.add(new Genero("Anime",R.drawable.anime));
        listaDeGeneros.add(new Genero("Aventura",R.drawable.aventura));
        listaDeGeneros.add(new Genero("Ciencia Ficcion",R.drawable.ciencia_ficcion));
        listaDeGeneros.add(new Genero("Comedia",R.drawable.comedia));
        listaDeGeneros.add(new Genero("Deporte",R.drawable.deporte));
        listaDeGeneros.add(new Genero("Documentales",R.drawable.documental));
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

    interface EscuchadorDeGeneros{
        public void seleccionaronGenero(Genero unGenero);
    }
}
