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

public class AdaptadorDeSeriesFavoritasRecycler extends RecyclerView.Adapter  {
    private List<Serie> listaDeSeriesfavoritas;
    private Context unContexto;
    private EscuchadorDeFavoritos escuchadorDeFavoritos;

    public AdaptadorDeSeriesFavoritasRecycler(List<Serie> listaDeSeriesfavoritas, Context unContexto, EscuchadorDeFavoritos escuchadorDeFavoritos) {
        this.listaDeSeriesfavoritas = listaDeSeriesfavoritas;
        this.unContexto = unContexto;
        this.escuchadorDeFavoritos = escuchadorDeFavoritos;
    }

    //Crea la celda

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celda = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);
        SeriesFavoritasViewHolder seriesFavoritasViewHolder = new SeriesFavoritasViewHolder(celda);
        return seriesFavoritasViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final  Serie unaSerie = listaDeSeriesfavoritas.get(position);
        SeriesFavoritasViewHolder seriesFavoritasViewHolder = (SeriesFavoritasViewHolder) holder;
        seriesFavoritasViewHolder.cargarSerieFavorita(unaSerie);

        View viewDeLaCelda = seriesFavoritasViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aca va el coidog que quiero que ejecute cuando se selecciona una celda
                escuchadorDeFavoritos.seleccionaronA(unaSerie);
                //Escuchador.Seleccionaron a (unaSerie)

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeSeriesfavoritas.size();
    }

    private class SeriesFavoritasViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDeLaPeliculaOSerieFavorita;
        private TextView textViewNombreDeLaPeliculaOSerieFavorita;


        public SeriesFavoritasViewHolder(View itemView) {
            super(itemView);
            imageViewDeLaPeliculaOSerieFavorita = (ImageView) itemView.findViewById(R.id.imageViewDeLaPeliculaOSerieFavorita);
            //textViewNombreDeLaPeliculaOSerieFavorita = (TextView)itemView.findViewById(R.id.textViewNombreDeLaPeliculaOSerieFavorita);
        }

        public void cargarSerieFavorita(Serie unaSerie){
            textViewNombreDeLaPeliculaOSerieFavorita.setText(unaSerie.getName());
            //imageViewDeLaPeliculaOSerieFavorita.setImageResource(unaSerie.getPoster_path());
        }
    }

    public interface  EscuchadorDeFavoritos {
        public void seleccionaronA (Serie unaSerie);
    }
}
