package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Genero;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

/**
 * Created by ma on 11/11/17.
 */

public class SeriesAdapter extends RecyclerView.Adapter {
    private List<Serie> listaDeGenerosDeSeries;
    private Context unContext;
    private EscuchadorDeSerie escuchadorDeSeries;


    public SeriesAdapter(List<Serie> listaDeGenerosDeSeries, Context unContext, SeriesFragment escuchadorDeSeries) {
        this.listaDeGenerosDeSeries = listaDeGenerosDeSeries;
        this.unContext = unContext;
        this.escuchadorDeSeries = escuchadorDeSeries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.celda_series, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder (celda);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Serie unaSerie = listaDeGenerosDeSeries.get(position);
        SeriesViewHolder seriesViewHolder = (SeriesViewHolder)holder;
        seriesViewHolder.cargarSerie(unaSerie);

        View viewDeLaCelda = seriesViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escuchadorDeSeries.seleccionaronA(unaSerie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeGenerosDeSeries.size();
    }


    private class SeriesViewHolder extends RecyclerView.ViewHolder{
        TextView textViewSerie = itemView.findViewById(R.id.textViewCeldaSeries);
        ImageView imagenSerie = itemView.findViewById(R.id.imageViewCeldaSeries);

        public SeriesViewHolder(View itemView) {
            super(itemView);
        }

        public void cargarSerie (Serie unaSerie){
            textViewSerie.setText(unaSerie.getGeneroSerie());
            imagenSerie.setImageResource(unaSerie.getImagenSerie());
        }
    }

    public interface EscuchadorDeSerie {
        public void seleccionaronA(Serie unaSerie);
    }
}


