package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetalleGenerosActivity extends AppCompatActivity implements AdaptadorDeListaDePeliculasRecycler.PeliculasListener{
    private List<Pelicula> listaDePelis;
    private AdaptadorDeListaDePeliculasRecycler unAdaptadorDePeliculas;
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

        unAdaptadorDePeliculas = new AdaptadorDeListaDePeliculasRecycler(listaDePelis,this,this);

        recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewPersonaje.setHasFixedSize(true);

        recyclerViewPersonaje.setAdapter(unAdaptadorDePeliculas);
        
    }

    //Hacer un if para cuando toquen el genero de terror aparesca una lista con peliculas de terror.

    private void cargarPelis() {

            listaDePelis = new ArrayList<>();
            listaDePelis.add(new Pelicula("El transportador 10", R.drawable.imagenpeliculas, "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado."));
            listaDePelis.add(new Pelicula("Indiana jones", R.drawable.imagenpeliculas, "Aventura", "Un exploraGay que siempre lleva su latigo encima."));
            listaDePelis.add(new Pelicula("Martes 13", R.drawable.imagenpeliculas, "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos."));
            listaDePelis.add(new Pelicula("Masacre de texas", R.drawable.imagenpeliculas,"Terror","Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas"));
            listaDePelis.add(new Pelicula("Anabelle",R.drawable.imagenpeliculas,"Terror","Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas"));
            listaDePelis.add(new Pelicula("Freddy Cruger",R.drawable.imagenpeliculas,"Terror","Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza "));
            listaDePelis.add(new Pelicula("IT", R.drawable.imagenpeliculas,"Terror","Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio"));
            listaDePelis.add(new Pelicula("SAW",R.drawable.imagenpeliculas,"Terror", "Un viejo loco asesina a toda persona culpable de pecados"));



    }


    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        Toast.makeText(this,unaPeli.getNombre(), Toast.LENGTH_SHORT).show();
        Intent unIntent = new Intent(this, DetallePeliculaActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_pelicula",unaPeli.getNombre());
        unBundle.putInt("Imagen_Pelicula", unaPeli.getImagen());
        unBundle.putString("Genero_Pelicula",unaPeli.getGenero());
        unBundle.putString("descripcion_pelicula",unaPeli.getDescripcion());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
