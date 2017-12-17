package a0817moact03c_2.a0817moact03c_02.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import a0817moact03c_2.a0817moact03c_02.View.Activities.FavoritosActivity;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Activities.LoginActivity;
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


    private void printHash(){
        PackageInfo info;
        try {

            info = getPackageManager().getPackageInfo(
                    getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
                System.out.println("Hash key" + something);
            }

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

    }
    private ViewPager unViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printHash();

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
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                switch (item.getItemId()){
                    case R.id.Inicio :
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        break;
                    case R.id.Login :
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        break;
                    case R.id.Logout :
                        if(user != null) {
                            FirebaseAuth.getInstance().signOut();
                            LoginManager.getInstance().logOut();
                            Toast.makeText(getApplicationContext(),"Logout completado",Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(getApplicationContext(),"No estas registrado",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.FavoritosItem :
                        if (user != null) {
                            startActivity(new Intent(MainActivity.this, FavoritosActivity.class));
                        }else {
                            showDialog(MainActivity.this,"No estas registrado.", "tienes que iniciar sesión para completar la siguiente accion.");
                        }
                        break;
                    case R.id.Action :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("28","Accion"));
                        break;
                    case R.id.Adventure :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("12","Aventura"));
                        break;
                    case R.id.Animation :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("16","Animadas"));
                        break;
                    case R.id.ScienceFiction :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("878","Ciencia Ficción"));
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
                    /*case R.id.Family :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10751","Familiar"));
                        break;
                    case R.id.Fantasy :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("14","Fantasia"));
                        break;*/
                    case R.id.History :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("36","Historia"));
                        break;
                    case R.id.Horror :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("27","Terror/Suspenso"));
                        break;
                    case R.id.Music :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10402","Comedia musical"));
                        break;
                   /* case R.id.Mystery :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("9648","Misterio"));
                        break;*/
                    case R.id.Romance :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10749","Romanticas"));
                        break;
                   /* case R.id.Thriller :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("53","Thriller"));
                        break;*/
                    case R.id.War :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("10752","Guerra"));
                        break;
                   /* case R.id.Western :
                        cargadorDeFragments(new FragmentoGenerosPantallaPrincipal("37","Vaqueros"));
                        break;*/
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
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.contenedorDeFragmentsDeGenerosPantallaPrincipal,unFragment);
        fragmentTransaction.commit();
    }
    public void showDialog(final Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.MyDialogTheme);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
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
        unBundle.putString("id",unaSerie.getId());
        unBundle.putString("descripcion_serie",unaSerie.getOverview());
        unBundle.putString("imagen_serie",unaSerie.getPoster_path());
        unBundle.putInt("posicion_serie",unaSerie.getPosicion());
        unBundle.putString("idDeSerie", unaSerie.getId());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        Log.d("Nav", "Open");
        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                Log.d("Nav", "Close");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {
        Intent unIntent = new Intent(this, DetallePeliculaActivity.class);
        Bundle unBundle =  new Bundle();
        unBundle.putString(DetallePeliculaFragment.NOMBRE_PELICULA,unaPelicula.getNombre());
        unBundle.putInt(DetallePeliculaFragment.POSICION_PELICULA,unaPelicula.getPosicion());
        unBundle.putString(DetallePeliculaFragment.ID_PELICULA,unaPelicula.getId());
//        unBundle.putString(DetallePeliculaFragment.GENERO_PELICULA,unaPelicula.getGenre_ids());
        unBundle.putString(DetallePeliculaFragment.DESCRIPCION_PELICULA,unaPelicula.getOverview());
        unBundle.putString(DetallePeliculaFragment.IMAGEN_PELICULA,unaPelicula.getPoster_path());
        unBundle.putString(DetallePeliculaFragment.FECHAS_ESTRENO_PELICULA,unaPelicula.getRelease_date());
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
