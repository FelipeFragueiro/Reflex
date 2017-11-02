package a0817moact03c_2.a0817moact03c_02;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleGenerosFragment extends Fragment implements AdaptadorDeListaDePeliculasRecycler.PeliculasListener {
    private List<Pelicula> listaDePelis;
    private AdaptadorDeListaDePeliculasRecycler unAdaptadorDePelis;
    private AdaptadorDeListaDePeliculasRecycler.PeliculasListener escuchadorDePeliculas;
    private EscuchadorDePelis unEscuchadorDePelis;

    public DetalleGenerosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        unEscuchadorDePelis = (DetalleGenerosFragment.EscuchadorDePelis)context;
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

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(unAdaptadorDePelis);

        return view;
    }

    private void cargarPelis() {

        listaDePelis = new ArrayList<>();
        listaDePelis.add(new Pelicula("El transportador 10", R.drawable.imagenpeliculas, "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado."));
        listaDePelis.add(new Pelicula("Indiana jones", R.drawable.imagenpeliculas, "Aventura", "Un exploraGay que siempre lleva su latigo encima."));
        listaDePelis.add(new Pelicula("Martes 13", R.drawable.imagenpeliculas, "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos."));


    }

    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        unEscuchadorDePelis.seleccionaronPeli(unaPeli);

    }

    interface EscuchadorDePelis{
        public void seleccionaronPeli(Pelicula unaPeli);
    }
}
