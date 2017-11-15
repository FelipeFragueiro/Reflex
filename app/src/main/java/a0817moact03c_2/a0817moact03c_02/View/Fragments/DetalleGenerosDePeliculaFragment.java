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
                Toast.makeText(getActivity(), resultado.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        peliculasController.getMoviesList(escuchadorDeLaVista,getContext());

        listaDePelis = new ArrayList<>();
        listaDePelis.add(new Pelicula("El transportador", R.drawable.el_transportador,"1", "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado.",1,"1"));
        listaDePelis.add(new Pelicula("Indiana jones", R.drawable.indiana_jones,"1", "Aventura", "Un exploraGay que siempre lleva su latigo encima.",2,"1"));
        listaDePelis.add(new Pelicula("Martes 13", R.drawable.martes_13,"1", "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos.",3,"1"));
        listaDePelis.add(new Pelicula("Masacre de texas", R.drawable.masacre_de_texas,"1","Terror","Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas",4,"1"));
        listaDePelis.add(new Pelicula("Anabelle",R.drawable.anabelle,"1","Terror","Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas",5,"1"));
        listaDePelis.add(new Pelicula("Freddy Cruger",R.drawable.freddy_cruger,"1","Terror","Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza ",6,"1"));
        listaDePelis.add(new Pelicula("IT", R.drawable.it,"1","Terror","Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio",7,"1"));
        listaDePelis.add(new Pelicula("SAW",R.drawable.saw,"1","Terror", "Un viejo loco asesina a toda persona culpable de pecados",8,"1"));


    }

    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        unEscuchadorDePelis.seleccionaronPeli(unaPeli);

    }

    public interface EscuchadorDePelis{
        public void seleccionaronPeli(Pelicula unaPeli);
    }
}
