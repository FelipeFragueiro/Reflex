package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

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
        ListaGenerosFragment listaGenerosFragment = new ListaGenerosFragment();
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
        unBundle.putString("nombre_pelicula",unaPeli.getNombre());
        unBundle.putInt("Imagen_Pelicula", unaPeli.getImagen());
        unBundle.putString("Genero_Pelicula",unaPeli.getGenero());
        unBundle.putString("descripcion_pelicula",unaPeli.getDescripcion());
        unBundle.putInt("posicion_pelicula",unaPeli.getPosicion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
