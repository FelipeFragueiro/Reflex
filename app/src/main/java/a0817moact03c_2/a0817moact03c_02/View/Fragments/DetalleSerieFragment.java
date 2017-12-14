package a0817moact03c_2.a0817moact03c_02.View.Fragments;


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

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Controller.SeriesController;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeSeries;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSerieFragment extends Fragment implements AdaptadorDeSeries.EscuchadorDeSerie, View.OnClickListener {
    private List<Serie> listaDeSeries;
    private List<Serie> listaDeSeriesSimilares;
    private AdaptadorDeSeries adaptadorDeSeries;
    //private EscuchadorDeSeriesInterface escuchadorDeSeriesInterface;

    public DetalleSerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_detalle_serie, container, false);
        Bundle aBundle = getArguments();
        FloatingActionButton botonFlotante = (FloatingActionButton) fragmentView.findViewById(R.id.fadSerieFavoritos);
        botonFlotante.setOnClickListener(this);

        String nombreSerie = aBundle.getString("nombre_serie");
        String idDeSerie = aBundle.getString("idDeSerie");
        String generoSerie = aBundle.getString("genero_serie");
        String descripcionSerie = aBundle.getString("descripcion_serie");
        final String imagenSerie = aBundle.getString("imagen_serie");

        TextView textViewNombreSerie = (TextView) fragmentView.findViewById(R.id.textViewDelTituloDeLaSerieDetalle);
        ImageView unImageViewSerie = (ImageView) fragmentView.findViewById(R.id.imageViewDeLaSerieDetalle);
        TextView unTextViewDelGenero = (TextView) fragmentView.findViewById(R.id.textViewDelGeneroDeLaSerieDetalle);
        TextView unTextViewDeLaDescripcion = (TextView) fragmentView.findViewById(R.id.textViewDeLaDescripcionDeLaSerieDetalle);

     //   textViewNombreSerie.setText(nombreSerie);
        unTextViewDelGenero.setText(generoSerie);
        textViewNombreSerie.setText(nombreSerie);
        unTextViewDeLaDescripcion.setText(descripcionSerie);
        Glide.with(getContext()).load(imagenSerie).into(unImageViewSerie);

        listaDeSeries = new ArrayList<>();
        //RecyclerView recyclerViewSeries = (RecyclerView)fragmentView.findViewById(R.id.rec)
        SeriesController peliculasController = new SeriesController();


        RecyclerView recyclerViewDeSimilares = (RecyclerView)fragmentView.findViewById(R.id.recyclerViewDeSeriesSugeridas);
        listaDeSeriesSimilares = new ArrayList<>();
        cargarSeriesSimilares(idDeSerie);
        recyclerViewDeSimilares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptadorDeSeries = new AdaptadorDeSeries(listaDeSeriesSimilares,getContext(),this);
        recyclerViewDeSimilares.setAdapter(adaptadorDeSeries);

        /*ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeries.clear();
                listaDeSeries = resultado;
                adaptadorDePeliculaRecycler.notifyDataSetChanged();

                //escuchadorDePeliculasInterface.seleccionaronPelicula();
            }
        };

        peliculasController.getMoviesList(escuchadorDeLaVista,getActivity());
*/

        return fragmentView;
    }
    public void agregarAFavoritos(View view){
        //Primero tendria que iniciar sesion con firebase o facebook y poner el nombre en favoritos para luego hacer un if
        //hacer un listener para saber cuando hacen click en la pelicula seleccionada
        //crear nueva clase favoritos donde se agregen los datos de la pelicula y el nombre del usuario/ID
        //subir las cosas a baseDedatos de firebase
        //ir a la actividad de davoritos y bajar datos de firebase bajando las peliculas macheadas
        // con el id/nombre de usuario
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference pelifavorita = databaseReference.child("Favoritos").child(mAuth.getCurrentUser().getUid());


        Bundle aBundle = getArguments();
        String nombreSerie = aBundle.getString("nombre_serie");
        String generoSerie = aBundle.getString("genero_serie");
        String descripcionSerie = aBundle.getString("descripcion_serie");
        String id = aBundle.getString("id");
        final String imagenSerie = aBundle.getString("imagen_serie");

        PeliculaFavorita peliculaFavorita = new PeliculaFavorita();
        peliculaFavorita.setId(id);
        peliculaFavorita.setGenre(generoSerie);
        peliculaFavorita.setTitle(nombreSerie);
        peliculaFavorita.setPoster_path(imagenSerie);
        peliculaFavorita.setOverview(descripcionSerie);
        peliculaFavorita.setSerieOpeli("serie");


        DatabaseReference newpelifavoritaref = pelifavorita.push();
        peliculaFavorita.setUserID(mAuth.getCurrentUser().getUid());
        peliculaFavorita.setKey(newpelifavoritaref.getKey());
        newpelifavoritaref.setValue(peliculaFavorita);



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fadSerieFavoritos:

                agregarAFavoritos(view);

                break;
            case R.id.fadSerieCompartir:
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
    private void cargarSeriesSimilares(final String unId) {

        SeriesController seriesController = new SeriesController();

        ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaDeSeriesSimilares.clear();
                listaDeSeriesSimilares.addAll(resultado);
                adaptadorDeSeries.notifyDataSetChanged();
                ///cargarActoresRelacionados(unId);
            }
        };

        seriesController.getSerieSimilarList(escuchadorDeLaVista, getContext(),unId);

    }

    @Override
    public void seleccionaronA(Serie unaSerie) {

    }
}
