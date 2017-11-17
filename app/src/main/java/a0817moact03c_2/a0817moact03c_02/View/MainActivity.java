package a0817moact03c_2.a0817moact03c_02.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Genero;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetalleGenerosDePeliculaActivity;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetallePeliculaActivity;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetalleSeriesActivity;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.ListaGenerosDePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.PantallaPrincipalFragmentPeliculas;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

public class MainActivity extends AppCompatActivity implements SeriesFragment.EscuchadorDeSeries ,PantallaPrincipalFragmentPeliculas.EscuchadorDePelicula{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String[] PAGE_TITLES = new String[] {
            "Peliculas",
            "Series",
            "Trailers"
    };

    private final android.support.v4.app.Fragment[] PAGES = new android.support.v4.app.Fragment[] {
            new PantallaPrincipalFragmentPeliculas(),
            new SeriesFragment(),
    };

    private ViewPager unViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        unViewPager = (ViewPager)findViewById(R.id.viewPagerPeliculasYSeries);
        unViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(unViewPager);

    }

    @Override
    public void seleccionaronSerie(Serie unaSerie) {
        Intent unIntent = new Intent(this, DetalleSeriesActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_serie",unaSerie.getNombreSerie());
        unBundle.putString("genero_serie",unaSerie.getGeneroSerie());
        unBundle.putString("descripcion_serie",unaSerie.getDescripcionSerie());
        unBundle.putInt("imagen_serie",unaSerie.getImagenSerie());
        unBundle.putInt("posicion_serie",unaSerie.getPosicion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {
        Intent unIntent = new Intent(this, DetallePeliculaActivity.class);
        Bundle unBundle =  new Bundle();
        unBundle.putString("nombre_pelicula",unaPelicula.getNombre());
        unBundle.putInt("posicion_pelicula",unaPelicula.getPosicion());
        unBundle.putInt("id_pelicula",unaPelicula.getId());
        unBundle.putString("genre_pelicula",unaPelicula.getGenre());
        unBundle.putString("overview_pelicula",unaPelicula.getOverview());
        unBundle.putString("poster_path_pelicula",unaPelicula.getPoster_path());
        unBundle.putString("release_date_pelicula",unaPelicula.getRelease_date());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }

    /*public void onClickPelicula(View view){
        TextView textView = (TextView)findViewById(R.id.textviewTitulo);
        textView.setText("PELICULAS");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaGenerosDePeliculaFragment listaGenerosDePeliculaFragment = new ListaGenerosDePeliculaFragment();
        fragmentTransaction.replace(R.id.contenedorFragmentPeliculasYSeries, listaGenerosDePeliculaFragment);
        fragmentTransaction.commit();
    }

    public void onClickSerie(View view){
        TextView textView = (TextView)findViewById(R.id.textviewTitulo);
        textView.setText("SERIES");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaGenerosDePeliculaFragment listaGenerosDePeliculaFragment = new ListaGenerosDePeliculaFragment();
        fragmentTransaction.replace(R.id.contenedorFragmentPeliculasYSeries, listaGenerosDePeliculaFragment);
        fragmentTransaction.commit();
    }*/


    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

    /*@Override
    public void seleccionaronGenero(Genero unGenero) {
        Intent unIntent = new Intent(this, DetalleGenerosDePeliculaActivity.class);
        Bundle unBundle = new  Bundle();
        unBundle.putString("nombre_genero",unGenero.getNombreGenero());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }*/
}
