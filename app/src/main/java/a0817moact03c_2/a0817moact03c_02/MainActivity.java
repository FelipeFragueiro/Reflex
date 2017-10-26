package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdaptadorGenerosRecycler.GenerosListener{
    private List<Genero> listaDeGeneros;
    private AdaptadorGenerosRecycler unAdaptadorDeGeneros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarGeneros();

        RecyclerView recyclerViewPersonaje = (RecyclerView)findViewById(R.id.RecyclerGeneros);

        unAdaptadorDeGeneros = new AdaptadorGenerosRecycler(listaDeGeneros,this,this);

        recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));

        recyclerViewPersonaje.setAdapter(unAdaptadorDeGeneros);
    }

    private void cargarGeneros() {
        listaDeGeneros = new ArrayList<>();
        listaDeGeneros.add(new Genero("ACCION",R.drawable.accion));
        listaDeGeneros.add(new Genero("TERROR",R.drawable.terror));
        listaDeGeneros.add(new Genero("AVENTURA",R.drawable.aventura));

    }


   /* @Override
    public void seleccionaronA(Genero unGenero) {
        Toast.makeText(this,unGenero.getNombre(),Toast.LENGTH_SHORT).show();
        Intent unIntent = new Intent(this, activitypelis.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("genero",unGenero.getNombre());

        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }*/
}
