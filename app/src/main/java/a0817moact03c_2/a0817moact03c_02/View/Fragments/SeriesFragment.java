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


import a0817moact03c_2.a0817moact03c_02.Controller.SeriesController;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeSeries;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment implements AdaptadorDeSeries.EscuchadorDeSerie {


    private List<Serie> listaDeSeriesPopulares;
    private List<Serie> listaDeSeriesEnTV;
    private List<Serie> listaDeSeriesTop;
    private AdaptadorDeSeries adaptadorDeSeriesPopulares;
    private AdaptadorDeSeries adaptadorDeSeriesEnTV;
    private AdaptadorDeSeries adaptadorDeSeriesTop;
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

        View view = inflater.inflate(R.layout.fragment_series, container, false);

        RecyclerView recyclerViewSeriesPopulares = view.findViewById(R.id.recyclerViewSeriesPopulares);
        listaDeSeriesPopulares = new ArrayList<>();



        recyclerViewSeriesPopulares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        adaptadorDeSeriesPopulares = new AdaptadorDeSeries(listaDeSeriesPopulares,getContext(),this);

        recyclerViewSeriesPopulares.setAdapter(adaptadorDeSeriesPopulares);
        cargarSeriesPopulares();



        RecyclerView recyclerViewSeriesEnTV = view.findViewById(R.id.recyclerViewSeriesEnTV);
        listaDeSeriesEnTV = new ArrayList<>();


        recyclerViewSeriesEnTV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDeSeriesEnTV = new AdaptadorDeSeries(listaDeSeriesEnTV,getContext(),this);
        recyclerViewSeriesEnTV.setAdapter(adaptadorDeSeriesEnTV);
        cargarSeriesEnTV();

        RecyclerView recyclerViewSeriesTop = view.findViewById(R.id.recyclerViewSeriesTOP);
        listaDeSeriesTop = new ArrayList<>();

        recyclerViewSeriesTop.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adaptadorDeSeriesTop = new AdaptadorDeSeries(listaDeSeriesTop,getContext(),this);
        recyclerViewSeriesTop.setAdapter(adaptadorDeSeriesTop);
        cargarSeriesTop();


        return view;

    }
    private void cargarSeriesPopulares() {

        SeriesController peliculasController = new SeriesController();

        ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeriesPopulares.clear();
                listaDeSeriesPopulares.addAll(resultado);
                adaptadorDeSeriesPopulares.notifyDataSetChanged();
            }
        };

        peliculasController.getPopularSeriesList(escuchadorDeLaVista, getContext());

    }

    private void cargarSeriesTop() {

        SeriesController peliculasController = new SeriesController();

        ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeriesTop.clear();
                listaDeSeriesTop.addAll(resultado);
                adaptadorDeSeriesTop.notifyDataSetChanged();
            }
        };

        peliculasController.getTopSeriesList(escuchadorDeLaVista, getContext());

    }

    private void cargarSeriesEnTV() {

        SeriesController peliculasController = new SeriesController();

        ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeriesEnTV.clear();
                listaDeSeriesEnTV.addAll(resultado);
                adaptadorDeSeriesEnTV.notifyDataSetChanged();
            }
        };

        peliculasController.getSeriesEnTvList(escuchadorDeLaVista, getContext());

    }


    @Override
    public void seleccionaronA(Serie unaSerie) {
        escuchadorDeSeries.seleccionaronSerie(unaSerie);

    }

    public interface EscuchadorDeSeries{
        public void seleccionaronSerie(Serie unaSerie);
    }
}