package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by ma on 26/10/17.
 */

public class AdaptadorDeListaDePeliculasRecycler extends RecyclerView.Adapter {
    private List<Pelicula> peliculasAMostrar;
    private Context unContexto;
    private PeliculasListener peliculasListener;

    public AdaptadorDeListaDePeliculasRecycler(List<Pelicula> peliculasAMostrar, Context unContexto, PeliculasListener unlistener  ){
        this.peliculasAMostrar = peliculasAMostrar;
        this.unContexto = unContexto;
        this.peliculasListener = unlistener;
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celdaGenero = layoutInflater.inflate(R.layout.peliculas_celda,parent, false);
        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(celdaGenero);

        return peliculaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final  Pelicula unaPeli = peliculasAMostrar.get(position);
         unaPeli.setPosicion(position);
        PeliculaViewHolder peliculaViewHolder = (PeliculaViewHolder) holder;
        peliculaViewHolder.cargarPeli(unaPeli);
        View viewDeLaCelda = peliculaViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aca va el codigo que quiero que ejecute cuando se selecciona una celda
                peliculasListener.seleccionaronA(unaPeli);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculasAMostrar.size();
    }

    private class PeliculaViewHolder extends RecyclerView.ViewHolder{
        private TextView textviewNombePeli;
        private ImageView imageViewPeli;
        private TextView textViewDescripcionPeli;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            textviewNombePeli = (TextView)itemView.findViewById(R.id.TextviewPelicula);
            imageViewPeli = (ImageView)itemView.findViewById(R.id.ImageviewPelicula);
            textViewDescripcionPeli = (TextView)itemView.findViewById(R.id.TextviewDescripcionPelicula);
        }
        public  void cargarPeli(Pelicula unaPeli){
            textviewNombePeli.setText(unaPeli.getNombre());
            imageViewPeli.setImageResource(unaPeli.getImagenPelicula());
            textViewDescripcionPeli.setText(unaPeli.getDescripcionPelicula());
        }
    }
    public interface PeliculasListener{
        public void seleccionaronA(Pelicula unaPeli);
    }
}
