package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetallePeliculaActivity extends AppCompatActivity implements DetallePeliculaFragment.EscuchadorDePeliculasInterface {
    private List<Pelicula> listaDePeliculasSugeridas;
    private AdaptadorDePeliculaRecycler unAdaptadorDePeliculaRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        detallePeliculaFragment.setArguments(unBundle);
        fragmentTransaction.replace(R.id.contenedorFragmentDetallePelicula, detallePeliculaFragment);
        fragmentTransaction.commit();


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerDetallePelicula);
        AdaptadorDeDetallePeliculaFragment adapterViewPager = new AdaptadorDeDetallePeliculaFragment(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(unBundle.getInt("posicion_pelicula"));

    }




    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {
        Intent unIntent = new Intent(DetallePeliculaActivity.this, DetallePeliculaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_pelicula", unaPelicula.getNombre());
        unBundle.putInt("Imagen_Pelicula", unaPelicula.getImagen());
        unBundle.putString("Genero_Pelicula", unaPelicula.getGenero());
        unBundle.putString("descripcion_pelicula", unaPelicula.getDescripcion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    @Override
    public void seleccionaronImagen(int unInt) {
        Intent unIntent = new Intent(this, FotoCompletaPeliculaActivity.class);

        Bundle unBundle = new Bundle();
        unBundle.putInt("foto_completa", unInt);
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }
}
