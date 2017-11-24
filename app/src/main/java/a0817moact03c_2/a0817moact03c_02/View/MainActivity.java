package a0817moact03c_2.a0817moact03c_02.View;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Controller.SeriesController;
import a0817moact03c_2.a0817moact03c_02.Model.AdaptadorDeSeriesFavoritasRecycler;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetallePeliculaActivity;
import a0817moact03c_2.a0817moact03c_02.View.Activities.DetalleSeriesActivity;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.FragmentoGenerosPantallaPrincipal;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.PantallaPrincipalFragmentPeliculas;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

public class MainActivity extends AppCompatActivity implements SeriesFragment.EscuchadorDeSeries ,PantallaPrincipalFragmentPeliculas.EscuchadorDePelicula{

    private List<Serie>listaseriespopulares;
    private AdaptadorDeSeriesFavoritasRecycler adaptadorDeSeriesFavoritasRecycler;

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

        listaseriespopulares = new ArrayList<>();
        cargarSeriesPopulares();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        unViewPager = (ViewPager)findViewById(R.id.viewPagerPeliculasYSeries);
        unViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(unViewPager);



        NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);

          /* Tenemos que escuchar cuando se hace click en una de las opciones
            de nuestro menú. En el MainActivity crear el listener como clase
            privada*/


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Inicio :
                        cargadorDeFragments(new PantallaPrincipalFragmentPeliculas());
                        break;
                    case R.id.Action :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("28"));
                        break;
                    case R.id.Adventure :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("12"));
                        break;
                    case R.id.Animation :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("16"));
                        break;
                    case R.id.Comedy :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("35"));
                        break;
                    case R.id.Crime :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("80"));
                        break;
                    case R.id.Documentary :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("99"));
                        break;
                    case R.id.Drama :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("18"));
                        break;
                    case R.id.Family :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10751"));
                        break;
                    case R.id.Fantasy :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("14"));
                        break;
                    case R.id.History :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("36"));
                        break;
                    case R.id.Horror :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("27"));
                        break;
                    case R.id.Music :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10402"));
                        break;
                    case R.id.Mystery :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("9648"));
                        break;
                    case R.id.Romance :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10749"));
                        break;
                    case R.id.ScienceFiction :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("878"));
                        break;
                    case R.id.TVMovie :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10770"));
                        break;
                    case R.id.Thriller :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("53"));
                        break;
                    case R.id.War :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10752"));
                        break;
                    case R.id.Western :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("37"));
                        break;
                    default:
                        cargadorDeFragments(new PantallaPrincipalFragmentPeliculas());
                }

                DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawers();

                return true;
            }
        });



    }
    private void cargadorDeFragments(Fragment unFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragmentsDeGenerosPantallaPrincipal,unFragment);
        fragmentTransaction.commit();
    }

    private void cargarSeriesPopulares() {

        SeriesController seriesController = new SeriesController();

        ResultListener<List<Serie>> escuchadorDeLaVista = new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                listaseriespopulares.clear();
                listaseriespopulares.addAll(resultado);
               // adaptadorDeSeriesFavoritasRecycler.notifyDataSetChanged();
            }
        };

        seriesController.getPopularSeriesList(escuchadorDeLaVista, this);

    }

    @Override
    public void seleccionaronSerie(Serie unaSerie) {
        Intent unIntent = new Intent(this, DetalleSeriesActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_serie",unaSerie.getName());
        //unBundle.putString("genero_serie",unaSerie.getGenre_ids());
        unBundle.putString("descripcion_serie",unaSerie.getOverview());
        unBundle.putString("imagen_serie",unaSerie.getPoster_path());
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
