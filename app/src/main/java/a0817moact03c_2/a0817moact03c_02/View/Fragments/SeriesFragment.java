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


import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.SeriesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment implements SeriesAdapter.EscuchadorDeSerie {


    private List<Serie> serieList;
    private EscuchadorDeSeries escuchadorDeSeries;

    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDeSeries = (EscuchadorDeSeries) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        //TextView textView = view.findViewById(R.id.textViewPalabraGeneros);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGenerosDeSerie);
        serieList = new ArrayList<>();



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        SeriesAdapter adaptador = new SeriesAdapter(serieList,getContext(),this);

        recyclerView.setAdapter(adaptador);

        serieList.add(new Serie("Accion",R.drawable.accion));
        serieList.add(new Serie("Animadas",R.drawable.animadas));
        serieList.add(new Serie("Anime",R.drawable.anime));
        serieList.add(new Serie("Aventura",R.drawable.aventura));
        serieList.add(new Serie("Ciencia Ficcion",R.drawable.ciencia_ficcion));
        serieList.add(new Serie("Comedia",R.drawable.comedia));
        serieList.add(new Serie("Deporte",R.drawable.deportes));
        serieList.add(new Serie("Documentales",R.drawable.documentales));
        serieList.add(new Serie("Drama",R.drawable.drama));
        serieList.add(new Serie("Infantil",R.drawable.infantil));
        serieList.add(new Serie("Policiaca",R.drawable.policial));
        serieList.add(new Serie("Romantico",R.drawable.romantica));
        serieList.add(new Serie("Terror",R.drawable.terror));
        serieList.add(new Serie("Thriller",R.drawable.thriller));
        serieList.add(new Serie("Suspenso",R.drawable.suspenso));

        return view;

    }


    @Override
    public void seleccionaronA(Serie unaSerie) {
        escuchadorDeSeries.seleccionaronGenero(unaSerie);

    }

    public interface EscuchadorDeSeries{
        public void seleccionaronGenero(Serie unaSerie);
    }
}
