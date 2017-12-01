package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.PantallaPrincipalFragmentPeliculas;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

/**
 * Created by ma on 17/11/17.
 */

public class AdapterPantallaPrincipalPeliculas extends RecyclerView.Adapter  {
    private List<Pelicula> listaDePeliculas;
    private Context unContext;
    private EscuchadorDePelicula escuchadorDePelicula;


    public AdapterPantallaPrincipalPeliculas(List<Pelicula> listaDePeliculas, Context unContext, EscuchadorDePelicula escuchadorDePelicula) {
        this.listaDePeliculas = listaDePeliculas;
        this.unContext = unContext;
        this.escuchadorDePelicula = escuchadorDePelicula;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.celda_peliculas, parent, false);
        PeliculasViewHolder peliculasViewHolder = new PeliculasViewHolder(celda);
        return peliculasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Pelicula unaPelicula = listaDePeliculas.get(position);
        PeliculasViewHolder peliculasViewHolder = (PeliculasViewHolder) holder;
        peliculasViewHolder.cargarPelicula(unaPelicula);

        View viewDeLaCelda = peliculasViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escuchadorDePelicula.seleccionaronA(unaPelicula);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }


    private class PeliculasViewHolder extends RecyclerView.ViewHolder{
        ImageView imagenPelicula;

        public PeliculasViewHolder(View itemView) {
            super(itemView);
            imagenPelicula = itemView.findViewById(R.id.imageViewCeldaPeliculas);
        }

        public void cargarPelicula (Pelicula unaPelicula){
            Glide.with(unContext).load(unaPelicula.getPoster_path()).into(imagenPelicula);
        }
    }

    public interface EscuchadorDePelicula {
        public void seleccionaronA(Pelicula unaPelicula);
    }
}



