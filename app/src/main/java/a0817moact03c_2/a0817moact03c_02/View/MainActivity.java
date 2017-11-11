package a0817moact03c_2.a0817moact03c_02.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.PeliculasController;
import a0817moact03c_2.a0817moact03c_02.Model.Genero;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetalleGenerosDePeliculaActivity;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.ListaGenerosDePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

public class MainActivity extends AppCompatActivity implements ListaGenerosDePeliculaFragment.EscuchadorDeGeneros, SeriesFragment.EscuchadorDeSeries{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String[] PAGE_TITLES = new String[] {
            "Peliculas",
            "Series",
            "Trailers"
    };

    private final android.support.v4.app.Fragment[] PAGES = new android.support.v4.app.Fragment[] {
            new ListaGenerosDePeliculaFragment(),
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

        PeliculasController peliculasController = new PeliculasController();
        peliculasController.getPostList(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {

            }
        }, this);

        
    }

    @Override
    public void seleccionaronGenero(Serie unaSerie) {

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

    @Override
    public void seleccionaronGenero(Genero unGenero) {
        Intent unIntent = new Intent(this, DetalleGenerosDePeliculaActivity.class);
        Bundle unBundle = new  Bundle();
        unBundle.putString("nombre_genero",unGenero.getNombreGenero());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }
}
