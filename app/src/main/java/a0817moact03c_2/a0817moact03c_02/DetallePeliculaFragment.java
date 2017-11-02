package a0817moact03c_2.a0817moact03c_02;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdaptadorDePeliculaRecycler.EscuchadorDePeliculas {
    private List<Pelicula> listaDePeliculas;
    private AdaptadorDePeliculaRecycler adaptadorDePeliculaRecycler;
    private AdaptadorDePeliculaRecycler.EscuchadorDePeliculas escuchadorDePeliculas;
    private EscuchadorDePeliculasInterface escuchadorDePeliculasInterface;


    public DetallePeliculaFragment() {
        // Required empty public constructor
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePeliculasInterface = (DetallePeliculaFragment.EscuchadorDePeliculasInterface)context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);

        Bundle unBundle = getArguments();
        String nombrePelicula = unBundle.get("nombre_pelicula").toString();
        final int imagenDePelicula = (int) unBundle.get("Imagen_Pelicula");
        String generoDePelicula = unBundle.get("Genero_Pelicula").toString();
        String descripcionDePelicula = unBundle.getString("descripcion_pelicula").toString();
        TextView textViewNombrePelicula = (TextView) view.findViewById(R.id.textViewDelTituloDeLaPelicula);
        ImageView unImageViewPelicula = (ImageView) view.findViewById(R.id.imageViewDeLaPelicula);
        TextView unTextViewDelGenero = (TextView) view.findViewById(R.id.textViewDelGenero);
        TextView unTextViewDeLaDescripcion = (TextView) view.findViewById(R.id.textViewDeLaDescripcionDeLaPelicula);
        textViewNombrePelicula.setText(nombrePelicula);
        unImageViewPelicula.setImageResource(imagenDePelicula);
        unTextViewDelGenero.setText(generoDePelicula);
        unTextViewDeLaDescripcion.setText(descripcionDePelicula);

        unImageViewPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               escuchadorDePeliculasInterface.seleccionaronImagen(imagenDePelicula);
            }
        });


        listaDePeliculas = new ArrayList<>();
        cargarPelis();

        //1) Buscar el recycler View
        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) view.findViewById(R.id.recyclerViewDePeliculasSugeridas);

        //2) Crear el adaptador
        adaptadorDePeliculaRecycler = new AdaptadorDePeliculaRecycler(listaDePeliculas, getContext(), this);

        //Como quiero que muestre las celdas
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        // recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));


        //3) Setearle el adaptador al recycler view
        //listViewPersonajes.setAdapter(unAdaptadorDePersonaje);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        return view;
    }

    private void cargarPelis() {

        listaDePeliculas = new ArrayList<>();
        listaDePeliculas.add(new Pelicula("El transportador 10", R.drawable.imagenpeliculas, "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado."));
        listaDePeliculas.add(new Pelicula("Indiana jones", R.drawable.imagenpeliculas, "Aventura", "Un exploraGay que siempre lleva su latigo encima."));
        listaDePeliculas.add(new Pelicula("Martes 13", R.drawable.imagenpeliculas, "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos."));
        listaDePeliculas.add(new Pelicula("Masacre de texas", R.drawable.imagenpeliculas,"Terror","Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas"));
        listaDePeliculas.add(new Pelicula("Anabelle",R.drawable.imagenpeliculas,"Terror","Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas"));
        listaDePeliculas.add(new Pelicula("Freddy Cruger",R.drawable.imagenpeliculas,"Terror","Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza "));
        listaDePeliculas.add(new Pelicula("IT", R.drawable.imagenpeliculas,"Terror","Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio"));
        listaDePeliculas.add(new Pelicula("SAW",R.drawable.imagenpeliculas,"Terror", "Un viejo loco asesina a toda persona culpable de pecados"));

    }

    public void seleccionaronA(Pelicula unaPeli) {
        escuchadorDePeliculasInterface.seleccionaronPelicula(unaPeli);
    }

    interface EscuchadorDePeliculasInterface {
        public void seleccionaronPelicula(Pelicula unaPelicula);
        public void seleccionaronImagen(int unInt);
    }
}
