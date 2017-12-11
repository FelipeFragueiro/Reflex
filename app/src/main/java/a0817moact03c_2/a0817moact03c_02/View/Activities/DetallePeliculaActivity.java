package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculaRecycler;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

public class DetallePeliculaActivity extends AppCompatActivity implements DetallePeliculaFragment.CallBackDetallePeliculaFragment {
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




    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {
        Intent unIntent = new Intent(DetallePeliculaActivity.this, DetallePeliculaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetallePeliculaFragment.NOMBRE_PELICULA, unaPelicula.getNombre());
        unBundle.putString(DetallePeliculaFragment.IMAGEN_PELICULA, unaPelicula.getPoster_path());
        unBundle.putString("genre_pelicula", unaPelicula.getGenre());
        unBundle.putString(DetallePeliculaFragment.DESCRIPCION_PELICULA, unaPelicula.getOverview());
        unBundle.putInt("posicion_pelicula",unaPelicula.getPosicion());
        unBundle.putString("id_pelicula",unaPelicula.getId());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    @Override
    public void seleccionaronActor(Actores unActor) {
        Intent unIntent = new Intent(DetallePeliculaActivity.this, DetalleActoresActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_actor", unActor.getName());
        unBundle.putString("also_known_as_actor",unActor.getAlso_known_as());
        unBundle.putString("character_actor",unActor.getCharacter());
        unBundle.putString("profile_path_actor",unActor.getProfile_path());
        unBundle.putString("birthday_actor",unActor.getBirthday());
        unBundle.putString("biografia_actor",unActor.getBiography());

        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }


}
