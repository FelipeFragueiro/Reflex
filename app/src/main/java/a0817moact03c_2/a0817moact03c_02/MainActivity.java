package a0817moact03c_2.a0817moact03c_02;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListaGenerosDePeliculaFragment.EscuchadorDeGeneros {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ViewPager unViewPager = (ViewPager)findViewById(R.id.viewPagerPeliculasYSeries);
        AdaptadorViewPagerMenuPrincipal adaptadorViewPagerMenuPrincipal = new AdaptadorViewPagerMenuPrincipal(getSupportFragmentManager());
        unViewPager.setAdapter(adaptadorViewPagerMenuPrincipal);



        
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


    @Override
    public void seleccionaronGenero(Genero unGenero) {
        Intent unIntent = new Intent(this, DetalleGenerosDePeliculaActivity.class);
        Bundle unBundle = new  Bundle();
        unBundle.putString("nombre_genero",unGenero.getNombreGenero());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }
}
