package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDeDetallePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

public class DetallePeliculaActivity extends AppCompatActivity implements DetallePeliculaFragment.EscuchadorDePeliculasInterface {
    private List<Pelicula> listaDePeliculasSugeridas;
    private AdaptadorDePeliculaRecycler unAdaptadorDePeliculaRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();

        Toast.makeText(this, "hola pelicula", Toast.LENGTH_SHORT).show();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        detallePeliculaFragment.setArguments(unBundle);
        fragmentTransaction.replace(R.id.contenedorFragmentDetallePelicula, detallePeliculaFragment);
        fragmentTransaction.commit();


        /*ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerDetallePelicula);
        AdaptadorDeDetallePeliculaFragment adapterViewPager = new AdaptadorDeDetallePeliculaFragment(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(unBundle.getInt("posicion_pelicula"));*/
    }


    public void agregarAFavoritos(Pelicula unaPelicula){

    }

    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {
        Intent unIntent = new Intent(DetallePeliculaActivity.this, DetallePeliculaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_pelicula", unaPelicula.getNombre());
        unBundle.putString("Imagen_Pelicula", unaPelicula.getPoster_path());
        unBundle.putString("Genero_Pelicula", unaPelicula.getGenre());
        unBundle.putString("descripcion_pelicula", unaPelicula.getOverview());
        unBundle.putInt("posicion_pelicula",unaPelicula.getPosicion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    @Override
    public void seleccionaronImagen(String unInt) {

    }


}
