package a0817moact03c_2.a0817moact03c_02.View.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.ActoresController;
import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.Model.Trailer;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.Util.YoutubeFragment;
import a0817moact03c_2.a0817moact03c_02.View.Activities.LoginActivity;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeActoresRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdapterPantallaPrincipalPeliculas;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements AdapterPantallaPrincipalPeliculas.EscuchadorDePelicula, AdaptadorDeActoresRecycler.EscuchadorDeActores, View.OnClickListener {

    public static final java.lang.String NOMBRE_PELICULA = "nombre_pelicula";
    public static final java.lang.String IMAGEN_PELICULA = "Imagen_Pelicula";
    public static final java.lang.String DESCRIPCION_PELICULA = "overview_pelicula";
    public static final java.lang.String ID_PELICULA = "id_pelicula";
    public static final java.lang.String GENERO_PELICULA = "genre_ids";
    public static final java.lang.String POSICION_PELICULA = "posicion_pelicula";
    public static final java.lang.String FECHAS_ESTRENO_PELICULA = "release_date_pelicula";
    public static final String API_KEY = "AIzaSyACk2EQxyUQ1zgu3sZhrsmipskzuSs7bzc";
    private String VIDEO_ID = "J5cU6Y-oFU4";


    private List<Pelicula> listaDePeliculasSimilares;
    private List<Actores> listaDeActores;
    private List<Trailer> listaDeTrailers;
    private Actores actorADetallar;
    private AdapterPantallaPrincipalPeliculas adaptadorDePeliculaRecycler;
    private AdaptadorDeActoresRecycler adaptadorDeActoresRecycler;
    private CallBackDetallePeliculaFragment callBackDetallePeliculasInterfaceFragment;


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
        args.putString("genre_pelicula2",unaPelicula.getGenre_ids());
        args.putString("overview_pelicula2",unaPelicula.getOverview());
        args.putString("poster_path_pelicula2",unaPelicula.getPoster_path());
        args.putString("release_date_pelicula2",unaPelicula.getRelease_date());*/


        args.putString(NOMBRE_PELICULA, unaPelicula.getNombre());
        args.putInt(POSICION_PELICULA, unaPelicula.getPosicion());
        args.putString(ID_PELICULA, unaPelicula.getId());
        args.putString(GENERO_PELICULA, unaPelicula.getGenre_ids().toString());
        args.putString(DESCRIPCION_PELICULA, unaPelicula.getOverview());
        args.putString(IMAGEN_PELICULA, unaPelicula.getPoster_path());
        args.putString(FECHAS_ESTRENO_PELICULA, unaPelicula.getRelease_date());


        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }

    public void agregarAFavoritos(View view) {
        //Primero tendria que iniciar sesion con firebase o facebook y poner el nombre en favoritos para luego hacer un if
        //hacer un listener para saber cuando hacen click en la pelicula seleccionada
        //crear nueva clase favoritos donde se agregen los datos de la pelicula y el nombre del usuario/ID
        //subir las cosas a baseDedatos de firebase
        //ir a la actividad de davoritos y bajar datos de firebase bajando las peliculas macheadas
        // con el id/nombre de usuario
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference();
            DatabaseReference pelifavorita = databaseReference.child("Favoritos").child(mAuth.getCurrentUser().getUid());


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
            peliculaFavorita.setSerieOpeli("pelicula");
            DatabaseReference newpelifavoritaref = pelifavorita.push();
            peliculaFavorita.setUserID(mAuth.getCurrentUser().getUid());
            peliculaFavorita.setKey(newpelifavoritaref.getKey());
            newpelifavoritaref.setValue(peliculaFavorita);
        } else {
            showDialog(getActivity(), "No estas registrado.", "tienes que iniciar sesión para completar la siguiente accion.");

        }


        /*UserPhoto anUserPhoto = new UserPhoto();
        anUserPhoto.setId(mAuth.getCurrentUser().getUid());
        anUserPhoto.setName(mAuth.getCurrentUser().getDisplayName());
        anUserPhoto.setImage(unEditText.getText()+".jpg");
        newFotoRef.setValue(anUserPhoto);*/


        // Toast.makeText(getContext(),peliculaFavorita.toString(),Toast.LENGTH_SHORT).show();
    }


    public void showDialog(final Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        callBackDetallePeliculasInterfaceFragment = (CallBackDetallePeliculaFragment) context;

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

        //YouTubePlayerFragment youTubePlayerView = fragmentView.findViewById(R.id.youtubetrailermovie);


        FloatingActionButton botonFlotante = (FloatingActionButton) fragmentView.findViewById(R.id.fadFavoritos);
        botonFlotante.setOnClickListener(this);

        FloatingActionButton botonFlotanteShare = (FloatingActionButton) fragmentView.findViewById(R.id.fadCompartir);
        botonFlotanteShare.setOnClickListener(this);


        TextView textViewNombrePelicula = (TextView) fragmentView.findViewById(R.id.textViewDelNombreDelActorDetalle);
        //ImageView unImageViewPelicula = (ImageView) fragmentView.findViewById(R.id.youtubeTrailerMovie);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelPersonajeActorDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaBiografiaDelActorDetalle);
        textViewNombrePelicula.setText(unTitulo);
        //Glide.with(getContext()).load(unaImagen).into(unImageViewPelicula);
        unTextViewDelGenero.setText(unGenero);
        unTextViewDeLaDescripcion.setText(unaDescripcion);


        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDePeliculasSugeridas);
        listaDePeliculasSimilares = new ArrayList<>();
        cargarPelisSimilares(unId);
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDePeliculaRecycler = new AdapterPantallaPrincipalPeliculas(listaDePeliculasSimilares, getContext(), this);
        recyclerViewDePeliculasSugeridas.setAdapter(adaptadorDePeliculaRecycler);

        listaDeTrailers = new ArrayList<>();
        cargarMovieTrailer(unId);





        RecyclerView recyclerViewDeActores = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewDelRepartoPelicula);
        listaDeActores = new ArrayList<>();
        recyclerViewDeActores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDeActoresRecycler = new AdaptadorDeActoresRecycler(listaDeActores, getContext(), this);
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

        peliculasController.getMoviesSimilarList(escuchadorDeLaVista, getContext(), unId);

    }

    public void cargarMovieTrailer(final String id) {
        PeliculasController peliculasController = new PeliculasController();

        final ResultListener<List<Trailer>> listResultListener = new ResultListener<List<Trailer>>() {
            @Override
            public void finish(List<Trailer> resultado) {
                listaDeTrailers.clear();
                listaDeTrailers.addAll(resultado);
                //adaptadorDePeliculaRecycler.notifyDataSetChanged();
                Trailer trailer;
                trailer = listaDeTrailers.get(0);

                YoutubeFragment fragment = new YoutubeFragment();
                fragment.setKeyto(trailer.getKey());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.youtubeTrailerMovie, fragment).addToBackStack(null).commit();

            }
        };
        peliculasController.getMovieTrailer(listResultListener, getContext(), id);
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

        peliculasController.getMovieCredits(escuchadorDeLaVista, getContext(), unId);

    }

    private Actores cargarDetalleActor(String unId) {
        ActoresController actoresController = new ActoresController();

        ResultListener<Actores> escuchadorDeLaVista = new ResultListener<Actores>() {
            @Override
            public void finish(Actores resultado) {
                actorADetallar = resultado;
            }
        };

        actoresController.getActorDetail(escuchadorDeLaVista, getContext(), unId);
        return actorADetallar;
    }

    public void seleccionaronA(Pelicula unaPeli) {
        callBackDetallePeliculasInterfaceFragment.seleccionaronPelicula(unaPeli);
    }

    @Override
    public void seleccionaronA(Actores unActor) {
        callBackDetallePeliculasInterfaceFragment.seleccionaronActor(unActor);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fadFavoritos:

                agregarAFavoritos(view);

                break;
            case R.id.fadCompartir:
                //Creamos un share de tipo ACTION_SENT
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                //Indicamos que voy a compartir texto
                share.setType("text/plain");
                //Le agrego un título
                share.putExtra(Intent.EXTRA_SUBJECT, "Compartir en Instagram");
                //Le agrego el texto a compartir (Puede ser un link tambien)
                share.putExtra(Intent.EXTRA_TEXT, "Hola como estan");
                //Hacemos un start para que comparta el contenido.
                startActivity(Intent.createChooser(share, "Share link!"));
                break;
        }

    }






    public interface CallBackDetallePeliculaFragment {
        public void seleccionaronPelicula(Pelicula unaPelicula);

        public void seleccionaronActor(Actores unActor);
    }




}
