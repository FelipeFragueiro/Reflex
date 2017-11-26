package a0817moact03c_2.a0817moact03c_02.View;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
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

import static a0817moact03c_2.a0817moact03c_02.R.id.drawerLayout;
import static a0817moact03c_2.a0817moact03c_02.R.id.navigationView;

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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigationview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("28","Accion"));
                        break;
                    case R.id.Adventure :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("12","Aventura"));
                        break;
                    case R.id.Animation :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("16","Animacion"));
                        break;
                    case R.id.Comedy :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("35","Comedia"));
                        break;
                    case R.id.Crime :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("80","Crimen"));
                        break;
                    case R.id.Documentary :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("99","Documentales"));
                        break;
                    case R.id.Drama :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("18","Drama"));
                        break;
                    case R.id.Family :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10751","Familiar"));
                        break;
                    case R.id.Fantasy :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("14","Fantasia"));
                        break;
                    case R.id.History :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("36","Historia"));
                        break;
                    case R.id.Horror :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("27","Horror"));
                        break;
                    case R.id.Music :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10402","Musica"));
                        break;
                    case R.id.Mystery :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("9648","Misterio"));
                        break;
                    case R.id.Romance :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10749","Romance"));
                        break;
                    case R.id.ScienceFiction :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("878","Ciencia Ficcion"));
                        break;
                    case R.id.Thriller :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("53","Thriller"));
                        break;
                    case R.id.War :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10752","Guerra"));
                        break;
                    case R.id.Western :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("37","Vaqueros"));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        Log.d("NICK", "CWECNEWKVNERIPNVIEWNFVIPEWNVIPEWNVPIEWNVPIEWNVPIEWNVPIRWNVPRWVPO");
        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                Log.d("NICK", "CWECNEWKVNERIPNVIEWNFVIPEWNVIPEWNVPIEWNVPIEWNVPIEWNVPIRWNVPRWVPO");
                return true;

        }
        return super.onOptionsItemSelected(item);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;

        }
        return super.onOptionsItemSelected(item);
    }*/

    /*navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            // Handle menu item clicks here.
            drawerLayout.closeDrawers();  // CLOSE DRAWER
            return true;
        }
    });*/



    /*@Override
    public void seleccionaronGenero(Genero unGenero) {
        Intent unIntent = new Intent(this, DetalleGenerosDePeliculaActivity.class);
        Bundle unBundle = new  Bundle();
        unBundle.putString("nombre_genero",unGenero.getNombreGenero());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }*/
}
