package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
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

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdaptadorDePeliculaRecycler.EscuchadorDePeliculas {
    private List<Pelicula> listaDePeliculas;
    private AdaptadorDePeliculaRecycler adaptadorDePeliculaRecycler;
    private EscuchadorDePeliculasInterface escuchadorDePeliculasInterface;


    public DetallePeliculaFragment() {
        // Required em
        // pty public constructor
    }

    public static DetallePeliculaFragment dameUnDetallePeliculaFragment(Pelicula unaPelicula) {
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        Bundle args = new Bundle();
        args.putString("nombre", unaPelicula.getNombre());
        args.putString("imagen", unaPelicula.getPoster_path());
        args.putString("descripcion", unaPelicula.getOverview());
        args.putString("genero", unaPelicula.getGeneroPelicula());

        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePeliculasInterface = (DetallePeliculaFragment.EscuchadorDePeliculasInterface) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);
        Bundle aBundle = getArguments();
        String unTitulo = aBundle.getString("nombre");
        final int unaImagen = aBundle.getInt("imagen");
        String unaDescripcion = aBundle.getString("descripcion");
        String unGenero = aBundle.getString("genero");


        TextView textViewNombrePelicula = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaPelicula);
        ImageView unImageViewPelicula = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaPelicula);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGenero);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaPelicula);
        textViewNombrePelicula.setText(unTitulo);
        unImageViewPelicula.setImageResource(unaImagen);
        unTextViewDelGenero.setText(unGenero);
        unTextViewDeLaDescripcion.setText(unaDescripcion);

        unImageViewPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escuchadorDePeliculasInterface.seleccionaronImagen(unaImagen);
            }
        });


        listaDePeliculas = new ArrayList<>();
        cargarPelis();

        //1) Buscar el recycler View
        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDePeliculasSugeridas);

        //2) Crear el adaptador
        adaptadorDePeliculaRecycler = new AdaptadorDePeliculaRecycler(listaDePeliculas, getContext(), this);

        //Como quiero que muestre las celdas
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        // recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));


        //3) Setearle el adaptador al recycler view
        //listViewPersonajes.setAdapter(unAdaptadorDePersonaje);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        return fragmentView;
    }


    private void cargarPelis() {

        listaDePeliculas = new ArrayList<>();
        listaDePeliculas.add(new Pelicula("El transportador", R.drawable.el_transportador,"1", "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado.",0,"1"));
        listaDePeliculas.add(new Pelicula("Indiana jones", R.drawable.indiana_jones,"1", "Aventura", "Un exploraGay que siempre lleva su latigo encima.",1,"1"));
        listaDePeliculas.add(new Pelicula("Martes 13", R.drawable.martes_13,"1", "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos.",2,"1"));
        listaDePeliculas.add(new Pelicula("Masacre de texas", R.drawable.masacre_de_texas,"1", "Terror", "Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas",3,"1"));
        listaDePeliculas.add(new Pelicula("Anabelle", R.drawable.anabelle,"1", "Terror", "Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas",4,"1"));
        listaDePeliculas.add(new Pelicula("Freddy Cruger", R.drawable.freddy_cruger,"1", "Terror", "Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza ",5,"1"));
        listaDePeliculas.add(new Pelicula("IT", R.drawable.it,"1", "Terror", "Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio",6,"1"));
        listaDePeliculas.add(new Pelicula("SAW", R.drawable.saw,"1", "Terror", "Un viejo loco asesina a toda persona culpable de pecados",7,"1"));

    }

    public void seleccionaronA(Pelicula unaPeli) {
        escuchadorDePeliculasInterface.seleccionaronPelicula(unaPeli);
    }

    public interface EscuchadorDePeliculasInterface {
        public void seleccionaronPelicula(Pelicula unaPelicula);
        public void seleccionaronImagen(int unInt);
    }
}
