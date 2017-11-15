package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
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

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeListaDePeliculasRecycler;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleGenerosDePeliculaFragment extends Fragment implements AdaptadorDeListaDePeliculasRecycler.PeliculasListener {
    private List<Pelicula> listaDePelis;
    private AdaptadorDeListaDePeliculasRecycler unAdaptadorDePelis;
    private EscuchadorDePelis unEscuchadorDePelis;

    public DetalleGenerosDePeliculaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        unEscuchadorDePelis = (DetalleGenerosDePeliculaFragment.EscuchadorDePelis)context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_generos, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerGeneros);

        Bundle unBundle = getArguments();
        String unNombreDeGenero = unBundle.getString("nombre_genero");
        TextView unTextView = (TextView)view.findViewById(R.id.textViewGeneroDetalleGeneroAct);
        unTextView.setText(unNombreDeGenero);


        listaDePelis = new ArrayList<>();
        cargarPelis();


        RecyclerView recyclerViewPersonaje = (RecyclerView)view.findViewById(R.id.RecyclerGeneros);

        unAdaptadorDePelis = new AdaptadorDeListaDePeliculasRecycler(listaDePelis,getContext(),this);

        recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewPersonaje.setHasFixedSize(true);

        recyclerViewPersonaje.setAdapter(unAdaptadorDePelis);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(unAdaptadorDePelis);

        return view;
    }

    private void cargarPelis() {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePelis.clear();
                listaDePelis.addAll(resultado);
                unAdaptadorDePelis.notifyDataSetChanged();
            }
        };

        peliculasController.getMoviesList(escuchadorDeLaVista,getContext());

    }

    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        unEscuchadorDePelis.seleccionaronPeli(unaPeli);

    }

    public interface EscuchadorDePelis{
        public void seleccionaronPeli(Pelicula unaPeli);
    }
}
