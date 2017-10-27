package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetallePeliculaActivity extends AppCompatActivity implements AdaptadorDePeliculaRecycler.EscuchadorDePeliculas {
    private List<Pelicula> listaDePeliculasSugeridas;
    private AdaptadorDePeliculaRecycler unAdaptadorDePeliculaRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        String nombrePelicula = unBundle.get("nombre_pelicula").toString();
        TextView textViewNombrePelicula = (TextView)findViewById(R.id.textViewDelTituloDeLaPelicula);
        textViewNombrePelicula.setText(nombrePelicula);


        //1) Buscar el recycler View
        RecyclerView recyclerViewDePeliculasSugeridas = (RecyclerView)findViewById(R.id.recyclerViewDePeliculasSugeridas);

        cargarPersonajesFavoritos();

        //2) Crear el adaptador
         unAdaptadorDePeliculaRecycler = new AdaptadorDePeliculaRecycler(listaDePeliculasSugeridas, this,this);

        //Como quiero que muestre las celdas
        recyclerViewDePeliculasSugeridas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       // recyclerViewPersonaje.setLayoutManager(new GridLayoutManager(this,2));


        //3) Setearle el adaptador al recycler view
        //listViewPersonajes.setAdapter(unAdaptadorDePersonaje);
        recyclerViewDePeliculasSugeridas.setAdapter(unAdaptadorDePeliculaRecycler);

    }

    private void cargarPersonajesFavoritos(){
        listaDePeliculasSugeridas = new ArrayList<>();
        listaDePeliculasSugeridas.add(new Pelicula("MasacreDeTexas", R.drawable.masacredetexas,"terror"));
        listaDePeliculasSugeridas.add(new Pelicula("Anabelle", R.drawable.anabelle,"terror"));
        listaDePeliculasSugeridas.add(new Pelicula("Freddy Crugger ", R.drawable.freddycrugger,"terror"));
        listaDePeliculasSugeridas.add(new Pelicula(" IT ", R.drawable.it,"terror"));
        listaDePeliculasSugeridas.add(new Pelicula("Saw", R.drawable.saw,"terror"));

    }

    @Override
    public void seleccionaronA(Pelicula unaPelicula) {

    }


}
