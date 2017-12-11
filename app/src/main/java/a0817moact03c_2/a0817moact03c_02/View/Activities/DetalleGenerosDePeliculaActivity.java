package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeListaDePeliculasRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetalleGenerosDePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

public class DetalleGenerosDePeliculaActivity extends AppCompatActivity implements DetalleGenerosDePeliculaFragment.EscuchadorDePelis{
    private List<Pelicula> listaDePelis;
    private AdaptadorDeListaDePeliculasRecycler unAdaptadorDePeliculas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_genero);


        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();




        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetalleGenerosDePeliculaFragment detalleGenerosDePeliculaFragment = new DetalleGenerosDePeliculaFragment();
        detalleGenerosDePeliculaFragment.setArguments(unBundle);
        fragmentTransaction.replace(R.id.contenedorFragmentListaPeliculas, detalleGenerosDePeliculaFragment);
        fragmentTransaction.commit();


        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaGenerosDePeliculaFragment listaGenerosFragment = new ListaGenerosDePeliculaFragment();
        fragmentTransaction.replace(R.id.contenedorFragmentListaPeliculas, listaGenerosFragment);
        fragmentTransaction.commit();*/




    }

    //Hacer un if para cuando toquen el genero de terror aparesca una lista con peliculas de terror.





    public void seleccionaronA(Pelicula unaPeli) {
    }

    @Override
    public void seleccionaronPeli(Pelicula unaPeli) {
        Intent unIntent = new Intent(this, DetallePeliculaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetallePeliculaFragment.NOMBRE_PELICULA,unaPeli.getNombre());
        unBundle.putString(DetallePeliculaFragment.IMAGEN_PELICULA, unaPeli.getPoster_path());
        unBundle.putString(DetallePeliculaFragment.GENERO_PELICULA,unaPeli.getGenre());
        unBundle.putString(DetallePeliculaFragment.DESCRIPCION_PELICULA,unaPeli.getOverview());
        unBundle.putInt(DetallePeliculaFragment.POSICION_PELICULA,unaPeli.getPosicion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
