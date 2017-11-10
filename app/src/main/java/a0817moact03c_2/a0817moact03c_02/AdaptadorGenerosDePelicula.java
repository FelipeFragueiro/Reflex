package a0817moact03c_2.a0817moact03c_02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ma on 26/10/17.
 */

public class AdaptadorGenerosDePelicula extends RecyclerView.Adapter {
    private List<Genero> listaDeBotonesDeGenero;
    private Context unContext;
    private EscuchadorDeGeneros escuchadorDeGeneros;


    public AdaptadorGenerosDePelicula(List<Genero> listaDeBotonesDeGenero, Context unContext, EscuchadorDeGeneros escuchadorDeGeneros) {
        this.listaDeBotonesDeGenero = listaDeBotonesDeGenero;
        this.unContext = unContext;
        this.escuchadorDeGeneros = escuchadorDeGeneros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.celda, parent, false);
        GenerosViewHolder generosViewHolder = new GenerosViewHolder(celda);
        return generosViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Genero unGenero = listaDeBotonesDeGenero.get(position);
        GenerosViewHolder generosViewHolder = (GenerosViewHolder)holder;
        generosViewHolder.cargarGenero(unGenero);

        View viewDeLaCelda = generosViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            escuchadorDeGeneros.seleccionaronA(unGenero);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeBotonesDeGenero.size();
    }


    private class GenerosViewHolder extends RecyclerView.ViewHolder{
        TextView textViewGenero = itemView.findViewById(R.id.textViewCelda);
        ImageView imagenGenero = itemView.findViewById(R.id.imageViewCelda);

        public GenerosViewHolder(View itemView) {
            super(itemView);
        }

        public void cargarGenero (Genero unGenero){
            textViewGenero.setText(unGenero.getNombreGenero());
            imagenGenero.setImageResource(unGenero.getImagenGenero());
        }
    }

    public interface EscuchadorDeGeneros {
        public void seleccionaronA(Genero unGenero);
    }
}
