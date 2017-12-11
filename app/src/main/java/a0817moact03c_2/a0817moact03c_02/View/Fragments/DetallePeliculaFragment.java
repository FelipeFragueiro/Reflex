package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeActoresRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterPantallaPrincipalPeliculas;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula, AdaptadorDeActoresRecycler.EscuchadorDeActores, View.OnClickListener {

    public static final java.lang.String NOMBRE_PELICULA = "nombre_pelicula";
    public static final java.lang.String IMAGEN_PELICULA = "Imagen_Pelicula";
    public static final java.lang.String DESCRIPCION_PELICULA = "overview_pelicula";
    public static final java.lang.String ID_PELICULA = "id_pelicula" ;
    public static final java.lang.String GENERO_PELICULA = "genre_pelicula" ;
    public static final java.lang.String POSICION_PELICULA ="posicion_pelicula";
    public static final java.lang.String FECHAS_ESTRENO_PELICULA = "release_date_pelicula";

    private List<Pelicula> listaDePeliculasSimilares;
    private List<Actores>listaDeActores;
    private AdapterPantallaPrincipalPeliculas adaptadorDePeliculaRecycler;
    private AdaptadorDeActoresRecycler adaptadorDeActoresRecycler;
    private EscuchadorDePelicula escuchadorDePeliculasInterface;
    private AdaptadorDeActoresRecycler.EscuchadorDeActores escuchadorDeActores;



    public DetallePeliculaFragment() {
        // Required em
        // pty public constructor
    }

    public static DetallePeliculaFragment dameUnDetallePeliculaFragment(Pelicula unaPelicula) {
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        Bundle args = new Bundle();

        /*args.putString("nombre_pelicula2",unaPelicula.getNombre());
        args.putInt("posicion_pelicula2",unaPelicula.getPosicion());
        args.putString("id_pelicula2",unaPelicula.getId());
        args.putString("genre_pelicula2",unaPelicula.getGenre());
        args.putString("overview_pelicula2",unaPelicula.getOverview());
        args.putString("poster_path_pelicula2",unaPelicula.getPoster_path());
        args.putString("release_date_pelicula2",unaPelicula.getRelease_date());*/


        args.putString(NOMBRE_PELICULA,unaPelicula.getNombre());
        args.putInt(POSICION_PELICULA,unaPelicula.getPosicion());
        args.putString(ID_PELICULA,unaPelicula.getId());
        args.putString(GENERO_PELICULA,unaPelicula.getGenre());
        args.putString(DESCRIPCION_PELICULA,unaPelicula.getOverview());
        args.putString(IMAGEN_PELICULA,unaPelicula.getPoster_path());
        args.putString(FECHAS_ESTRENO_PELICULA,unaPelicula.getRelease_date());


        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }

    public void agregarAFavoritos(View view){
        //Primero tendria que iniciar sesion con firebase o facebook y poner el nombre en favoritos para luego hacer un if
        //hacer un listener para saber cuando hacen click en la pelicula seleccionada
        //crear nueva clase favoritos donde se agregen los datos de la pelicula y el nombre del usuario/ID
        //subir las cosas a baseDedatos de firebase
        //ir a la actividad de davoritos y bajar datos de firebase bajando las peliculas macheadas
        // con el id/nombre de usuario

        Bundle aBundle = getArguments();
        String unId = aBundle.getString(ID_PELICULA);
        String unTitulo = aBundle.getString(NOMBRE_PELICULA);
        final String unaImagen = aBundle.getString(IMAGEN_PELICULA);
        String unaDescripcion = aBundle.getString(DESCRIPCION_PELICULA);
        String unGenero = aBundle.getString(GENERO_PELICULA);

        PeliculaFavorita peliculaFavorita = new PeliculaFavorita();
        peliculaFavorita.setId(unId);
        peliculaFavorita.setGenre(unGenero);
        peliculaFavorita.setTitle(unTitulo);
        peliculaFavorita.setPoster_path(unaImagen);
        peliculaFavorita.setOverview(unaDescripcion);

        Toast.makeText(getContext(),peliculaFavorita.toString(),Toast.LENGTH_SHORT).show();





    }


    public void onAttach(Context context) {
        super.onAttach(context);
        escuchadorDePeliculasInterface = (DetallePeliculaFragment.EscuchadorDePelicula) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);
        Bundle aBundle = getArguments();
        String unId = aBundle.getString(ID_PELICULA);
        String unTitulo = aBundle.getString(NOMBRE_PELICULA);
        String unaImagen = aBundle.getString(IMAGEN_PELICULA);
        String unaDescripcion = aBundle.getString(DESCRIPCION_PELICULA);
        String unGenero = aBundle.getString(GENERO_PELICULA);


        FloatingActionButton botonFlotante = (FloatingActionButton) fragmentView.findViewById(R.id.fadFavoritos);
        botonFlotante.setOnClickListener(this);



        TextView textViewNombrePelicula = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaPeliculaDetalle);
        ImageView unImageViewPelicula = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaPeliculaDetalle);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGeneroDeLaPeliculaDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaPeliculaDetalle);
        textViewNombrePelicula.setText(unTitulo);
        Glide.with(getContext()).load(unaImagen).into(unImageViewPelicula);
        unTextViewDelGenero.setText(unGenero);
        unTextViewDeLaDescripcion.setText(unaDescripcion);




        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDePeliculasSugeridas);
        listaDePeliculasSimilares = new ArrayList<>();
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDePeliculaRecycler = new AdapterPantallaPrincipalPeliculas(listaDePeliculasSimilares, getContext(), (AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula) this);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        cargarPelisSimilares(unId);


        RecyclerView recyclerViewDeActores = (RecyclerView)fragmentView.findViewById(R.id.recyclerViewDelRepartoPelicula);
        listaDeActores = new ArrayList<>();
        recyclerViewDeActores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDeActoresRecycler = new AdaptadorDeActoresRecycler(listaDeActores,getContext(),escuchadorDeActores);
        recyclerViewDeActores.setAdapter(adaptadorDeActoresRecycler);
        return fragmentView;
    }

    private void cargarPelisSimilares(final String unId) {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Pelicula>> escuchadorDeLaVista = new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                listaDePeliculasSimilares.clear();
                listaDePeliculasSimilares.addAll(resultado);
                adaptadorDePeliculaRecycler.notifyDataSetChanged();
                cargarActoresRelacionados(unId);
            }
        };

        peliculasController.getMoviesSimilarList(escuchadorDeLaVista, getContext(),unId);

    }

    private void cargarActoresRelacionados(String unId) {

        PeliculasController peliculasController = new PeliculasController();

        ResultListener<List<Actores>> escuchadorDeLaVista = new ResultListener<List<Actores>>() {
            @Override
            public void finish(List<Actores> resultado) {
                listaDeActores.clear();
                listaDeActores.addAll(resultado);
                adaptadorDeActoresRecycler.setListaDeActores(listaDeActores);
                adaptadorDeActoresRecycler.notifyDataSetChanged();
            }
        };

        peliculasController.getMovieCredits(escuchadorDeLaVista, getContext(),unId);

    }




    public void seleccionaronA(Pelicula unaPeli) {
        escuchadorDePeliculasInterface.seleccionaronPelicula(unaPeli);
    }

    @Override
    public void seleccionaronA(Actores unActor) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fadFavoritos:

                agregarAFavoritos(view);

                break;
        }

    }

    public interface EscuchadorDePelicula {
        public void seleccionaronPelicula(Pelicula unaPelicula);
    }

}
