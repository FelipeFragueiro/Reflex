package a0817moact03c_2.a0817moact03c_02;


import android.content.Context;
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

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(unAdaptadorDePelis);

        return view;
    }

    private void cargarPelis() {

        listaDePelis = new ArrayList<>();
        listaDePelis.add(new Pelicula("El transportador", R.drawable.el_transportador, "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado.",1));
        listaDePelis.add(new Pelicula("Indiana jones", R.drawable.indiana_jones, "Aventura", "Un exploraGay que siempre lleva su latigo encima.",2));
        listaDePelis.add(new Pelicula("Martes 13", R.drawable.martes_13, "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos.",3));
        listaDePelis.add(new Pelicula("Masacre de texas", R.drawable.masacre_de_texas,"Terror","Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas",4));
        listaDePelis.add(new Pelicula("Anabelle",R.drawable.anabelle,"Terror","Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas",5));
        listaDePelis.add(new Pelicula("Freddy Cruger",R.drawable.freddy_cruger,"Terror","Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza ",6));
        listaDePelis.add(new Pelicula("IT", R.drawable.it,"Terror","Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio",7));
        listaDePelis.add(new Pelicula("SAW",R.drawable.saw,"Terror", "Un viejo loco asesina a toda persona culpable de pecados",8));


    }

    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        unEscuchadorDePelis.seleccionaronPeli(unaPeli);

    }

    interface EscuchadorDePelis{
        public void seleccionaronPeli(Pelicula unaPeli);
    }
}
