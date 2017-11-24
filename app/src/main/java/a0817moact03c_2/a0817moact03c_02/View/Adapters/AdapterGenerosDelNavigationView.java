package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by ma on 24/11/17.
 */

public class AdapterGenerosDelNavigationView extends RecyclerView.Adapter{
    private List<Pelicula> listaDePeliculasDeXGenero;
    private Context unContexto;
    private EscuchadorDePeliculasDelGenero unEscuchadorDePeliculas;

    public AdapterGenerosDelNavigationView(List<Pelicula> peliculasSugeridasAMostrar, Context unContexto, EscuchadorDePeliculasDelGenero unEscuchadorDePeliculas) {
        this.listaDePeliculasDeXGenero = peliculasSugeridasAMostrar;
        this.unContexto = unContexto;
        this.unEscuchadorDePeliculas = unEscuchadorDePeliculas;
    }



    //Crea la celda
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celda = layoutInflater.inflate(R.layout.celda_genero_navigation,parent, false);
        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(celda);
        return peliculaViewHolder;
    }

    //Combina la informacion con la celda
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Pelicula unaPelicula = listaDePeliculasDeXGenero.get(position);
        PeliculaViewHolder peliculaViewHolder = (PeliculaViewHolder) holder;
        peliculaViewHolder.cargarPelicula(unaPelicula);

        View viewDeLaCelda = peliculaViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aca va el coidog que quiero que ejecute cuando se selecciona una celda
                unEscuchadorDePeliculas.seleccionaronA((unaPelicula));
                //Escuchador.Seleccionaron a (unPersonaje)

            }
        });

    }

    //Cuantas celdas voy a mostrar
    @Override
    public int getItemCount() {
        return listaDePeliculasDeXGenero.size();
    }

    private class PeliculaViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNombreDeLaPelicula;
        private ImageView imageViewDeLaPelicula;


        public PeliculaViewHolder(View itemView) {
            super(itemView);
            textViewNombreDeLaPelicula = (TextView)itemView.findViewById(R.id.textViewNombreDeLaPeliculaDeXGenero);
            imageViewDeLaPelicula = (ImageView) itemView.findViewById(R.id.imageViewDePeliculaDeXGenero);
        }
        public void cargarPelicula(Pelicula unaPelicula){
            textViewNombreDeLaPelicula.setText(unaPelicula.getNombre());
            Glide.with(unContexto).load(unaPelicula.getPoster_path()).into(imageViewDeLaPelicula);
        }

    }

    public interface  EscuchadorDePeliculasDelGenero {
        public void seleccionaronA (Pelicula unaPelicula);
    }


}
