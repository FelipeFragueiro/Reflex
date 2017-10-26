package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdaptadorPeliculasRecycler.PeliculasListener{
    private List<Pelicula> listaDePelis;
    private AdaptadorPeliculasRecycler unAdaptadorDeGeneros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarPelis();

        RecyclerView recyclerViewPersonaje = (RecyclerView)findViewById(R.id.RecyclerGeneros);

        unAdaptadorDeGeneros = new AdaptadorPeliculasRecycler(listaDePelis,this,this);

        recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));

        recyclerViewPersonaje.setAdapter(unAdaptadorDeGeneros);
    }

    private void cargarPelis() {
        listaDePelis = new ArrayList<>();
        listaDePelis.add(new Pelicula("ACCION",R.drawable.accion));
        listaDePelis.add(new Pelicula("TERROR",R.drawable.terror));
        listaDePelis.add(new Pelicula("AVENTURA",R.drawable.aventura));

    }


    @Override
    public void seleccionaronA(Pelicula unaPeli) {
        Toast.makeText(this,unaPeli.getNombre(),Toast.LENGTH_SHORT).show();
        Intent unIntent = new Intent(this, activitypelis.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("Pelicula",unaPeli.getNombre());

        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
