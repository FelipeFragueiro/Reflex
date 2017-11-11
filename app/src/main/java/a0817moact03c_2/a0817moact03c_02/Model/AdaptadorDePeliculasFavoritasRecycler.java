package a0817moact03c_2.a0817moact03c_02.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by ma on 11/11/17.
 */

public class AdaptadorDePeliculasFavoritasRecycler extends RecyclerView.Adapter {
    private List<Pelicula> listaDePeliculasfavoritas;
    private Context unContexto;
    private EscuchadorDeFavoritos escuchadorDeFavoritos;

    public AdaptadorDePeliculasFavoritasRecycler(List<Pelicula> listaDePeliculasfavoritas,Context unContexto, EscuchadorDeFavoritos escuchadorDeFavoritos) {
        this.listaDePeliculasfavoritas = listaDePeliculasfavoritas;;
        this.unContexto = unContexto;
        this.escuchadorDeFavoritos = escuchadorDeFavoritos;
    }

    //Crea la celda

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celda = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);
        PeliculasFavoritasViewHolder peliculasFavoritasViewHolder = new PeliculasFavoritasViewHolder(celda);
        return peliculasFavoritasViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final  Pelicula unaPelicula = listaDePeliculasfavoritas.get(position);
        PeliculasFavoritasViewHolder peliculasFavoritasViewHolder = (PeliculasFavoritasViewHolder) holder;
        peliculasFavoritasViewHolder.cargarPeliculaFavorita(unaPelicula);

        View viewDeLaCelda = peliculasFavoritasViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aca va el coidog que quiero que ejecute cuando se selecciona una celda
                escuchadorDeFavoritos.seleccionaronA((unaPelicula));
                //Escuchador.Seleccionaron a (unaPelicula)

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDePeliculasfavoritas.size();
    }

    private class PeliculasFavoritasViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDeLaPeliculaOSerieFavorita;
        private TextView textViewNombreDeLaPeliculaOSerieFavorita;


        public PeliculasFavoritasViewHolder(View itemView) {
            super(itemView);
            imageViewDeLaPeliculaOSerieFavorita = (ImageView) itemView.findViewById(R.id.imageViewDeLaPeliculaOSerieFavorita);
            textViewNombreDeLaPeliculaOSerieFavorita = (TextView)itemView.findViewById(R.id.textViewNombreDeLaPeliculaOSerieFavorita);
        }

        public void cargarPeliculaFavorita(Pelicula unaPelicula){
            textViewNombreDeLaPeliculaOSerieFavorita.setText(unaPelicula.getNombre());
           // imageViewDeLaPeliculaOSerieFavorita.setImageResource(unaPelicula.getImagenPelicula());
        }
    }

    public interface  EscuchadorDeFavoritos {
        public void seleccionaronA (Pelicula unaPelicula);
    }

}
