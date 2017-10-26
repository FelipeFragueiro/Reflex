package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetalleGenerosActivity extends AppCompatActivity implements AdaptadorPeliculasRecycler.PeliculasListener{
    private List<Pelicula> listaDePelis;
    private AdaptadorPeliculasRecycler unAdaptadorDePeliculas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_genero);
        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();

        String unNombreDeGenero = unBundle.getString("nombre_genero");
        TextView unTextView = (TextView)findViewById(R.id.textViewGeneroDetalleGeneroAct);
        unTextView.setText(unNombreDeGenero);
        cargarPelis();

        RecyclerView recyclerViewPersonaje = (RecyclerView)findViewById(R.id.RecyclerGeneros);

        unAdaptadorDePeliculas = new AdaptadorPeliculasRecycler(listaDePelis,this,this);

        recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewPersonaje.setHasFixedSize(true);

        recyclerViewPersonaje.setAdapter(unAdaptadorDePeliculas);
        
    }

    //Hacer un if para cuando toquen el genero de terror aparesca una lista con peliculas de terror.

    private void cargarPelis() {

            listaDePelis = new ArrayList<>();
            listaDePelis.add(new Pelicula("El trolaso", R.drawable.accion, "Accion"));
            listaDePelis.add(new Pelicula("Indiana jones", R.drawable.terror, "Aventura"));
            listaDePelis.add(new Pelicula("pelicula terror", R.drawable.aventura, "Terror"));


    }


    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        //Toast.makeText(this,unaPeli.getNombre(),Toast.LENGTH_SHORT).show();
       // Intent unIntent = new Intent(this, detallePelis.class);
        //Bundle unBundle = new Bundle();
        //unBundle.putString("Pelicula",unaPeli.getNombre());

        //unIntent.putExtras(unBundle);
        //startActivity(unIntent);
    }
}
