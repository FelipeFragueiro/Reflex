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


import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeSeries;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment implements AdaptadorDeSeries.EscuchadorDeSerie {


    private List<Serie> listaDeSeriesPopulares;
    private List<Serie> listaDeSeriesEnTV;
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
        RecyclerView recyclerViewSeriesPopulares = view.findViewById(R.id.recyclerViewSeriesPopulares);
        listaDeSeriesPopulares = new ArrayList<>();



        recyclerViewSeriesPopulares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        AdaptadorDeSeries adaptadorDeSeriesPopulares = new AdaptadorDeSeries(listaDeSeriesPopulares,getContext(),this);

        recyclerViewSeriesPopulares.setAdapter(adaptadorDeSeriesPopulares);

        listaDeSeriesPopulares.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",0));
        listaDeSeriesPopulares.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",1));
        listaDeSeriesPopulares.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",2));
        listaDeSeriesPopulares.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",3));
        listaDeSeriesPopulares.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",4));

        RecyclerView recyclerViewSeriesEnTV = view.findViewById(R.id.recyclerViewSeriesEnTV);
        listaDeSeriesEnTV = new ArrayList<>();


        recyclerViewSeriesEnTV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        AdaptadorDeSeries adaptadorDeSeriesEnTV = new AdaptadorDeSeries(listaDeSeriesEnTV,getContext(),this);
        recyclerViewSeriesEnTV.setAdapter(adaptadorDeSeriesEnTV);
        listaDeSeriesEnTV.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",0));
        listaDeSeriesEnTV.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",1));
        listaDeSeriesEnTV.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",2));
        listaDeSeriesEnTV.add(new Serie("Prueba",R.drawable.martes_13,"prueba","prueba",3));
        listaDeSeriesEnTV.add(new Serie("Prueba",R.drawable.masacre_de_texas,"prueba","prueba",4));



        return view;

    }


    @Override
    public void seleccionaronA(Serie unaSerie) {
        escuchadorDeSeries.seleccionaronSerie(unaSerie);

    }

    public interface EscuchadorDeSeries{
        public void seleccionaronSerie(Serie unaSerie);
    }
}