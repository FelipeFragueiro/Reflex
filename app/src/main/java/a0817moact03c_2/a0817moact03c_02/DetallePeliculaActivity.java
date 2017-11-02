package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
        final int imagenDePelicula = (int) unBundle.get("Imagen_Pelicula");
        String generoDePelicula = unBundle.get("Genero_Pelicula").toString();
        String descripcionDePelicula = unBundle.getString("descripcion_pelicula").toString();
        TextView textViewNombrePelicula = (TextView)findViewById(R.id.textViewDelTituloDeLaPelicula);
        ImageView unImageViewPelicula = (ImageView)findViewById(R.id.imageViewDeLaPelicula);
        TextView unTextViewDelGenero = (TextView)findViewById(R.id.textViewDelGenero);
        TextView unTextViewDeLaDescripcion= (TextView) findViewById(R.id.textViewDeLaDescripcionDeLaPelicula);


        textViewNombrePelicula.setText(nombrePelicula);
        unImageViewPelicula.setImageResource(imagenDePelicula);
        unTextViewDelGenero.setText(generoDePelicula);
        unTextViewDeLaDescripcion.setText(descripcionDePelicula);

        unImageViewPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent unIntent = new Intent(DetallePeliculaActivity.this,FotoCompletaPeliculaActivity.class);

                Bundle unBundle = new Bundle();
                unBundle.putInt("foto_completa",imagenDePelicula);
                unIntent.putExtras(unBundle);
                startActivity(unIntent);
            }
        });

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
        listaDePeliculasSugeridas.add(new Pelicula("MasacreDeTexas", R.drawable.imagenpeliculas,"terror","Un pipe medio chiflado sale a matar gente en el estado de TeJas."));
        listaDePeliculasSugeridas.add(new Pelicula("Anabelle", R.drawable.imagenpeliculas,"terror", " Una muñeca de feria seenoja por no ser linda como la Barbie y mata a todos."));
        listaDePeliculasSugeridas.add(new Pelicula("Freddy Crugger ", R.drawable.imagenpeliculas,"terror","Un pelado quemado ,más achicharrado que una pasa de uva aparece en tus sueños para hacer contigo lo que se le plazca ."));
        listaDePeliculasSugeridas.add(new Pelicula(" IT ", R.drawable.imagenpeliculas,"terror", "Un payaso aparece cada 27 años en la matanza para robarse todos los celus."));
        listaDePeliculasSugeridas.add(new Pelicula("Saw", R.drawable.imagenpeliculas,"terror", " Mueren todos."));

    }

    @Override
    public void seleccionaronA(Pelicula unaPelicula) {

    }


}
