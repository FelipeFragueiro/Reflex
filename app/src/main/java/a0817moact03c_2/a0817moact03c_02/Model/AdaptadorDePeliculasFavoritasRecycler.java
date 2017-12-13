package a0817moact03c_2.a0817moact03c_02.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by ma on 11/11/17.
 */

public class AdaptadorDePeliculasFavoritasRecycler extends RecyclerView.Adapter {
    private List<PeliculaFavorita> listaDePeliculasfavoritas;
    private Context unContexto;
    private EscuchadorDeFavoritos escuchadorDeFavoritos;

    public AdaptadorDePeliculasFavoritasRecycler(Context unContexto, List<PeliculaFavorita> lista, EscuchadorDeFavoritos escuchadorDeFavoritos) {
        this.listaDePeliculasfavoritas = lista;
        this.unContexto = unContexto;
        this.escuchadorDeFavoritos = escuchadorDeFavoritos;
    }

    //Crea la celda
    public List<PeliculaFavorita> getLista(){
        return this.listaDePeliculasfavoritas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celda = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);
        PeliculasFavoritasViewHolder peliculasFavoritasViewHolder = new PeliculasFavoritasViewHolder(celda);
        return peliculasFavoritasViewHolder;
    }

    public PeliculaFavorita getObject(int position){
        PeliculaFavorita peliculaFavorita = new PeliculaFavorita();
        peliculaFavorita = listaDePeliculasfavoritas.get(position);
        return peliculaFavorita;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final  PeliculaFavorita unaPelicula = listaDePeliculasfavoritas.get(position);
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

        public void cargarPeliculaFavorita(PeliculaFavorita unaPelicula){
            textViewNombreDeLaPeliculaOSerieFavorita.setText(unaPelicula.getTitle());
            Glide.with(unContexto).load(unaPelicula.getPoster_path().toString()).into(imageViewDeLaPeliculaOSerieFavorita);

        }
    }

    public interface  EscuchadorDeFavoritos {
        public void seleccionaronA (PeliculaFavorita unaPelicula);
    }

}
