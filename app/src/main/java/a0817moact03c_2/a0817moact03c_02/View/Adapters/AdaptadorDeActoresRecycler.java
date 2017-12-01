package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by ma on 01/12/17.
 */

public class AdaptadorDeActoresRecycler extends RecyclerView.Adapter {
    private List<Actores>listaDeActores;
    private Context unContext;
    private EscuchadorDeActores escuchadorDeActores;

    public void setListaDeActores(List<Actores> listaDeActores) {
        this.listaDeActores = listaDeActores;
    }

    public AdaptadorDeActoresRecycler(List<Actores> listaDeActores, Context unContext, EscuchadorDeActores escuchadorDeActores) {
        this.listaDeActores = listaDeActores;
        this.unContext = unContext;
        this.escuchadorDeActores = escuchadorDeActores;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.celda_actores_pelicula, parent,false);
        ActoresViewHolder actoresViewHolder = new ActoresViewHolder(celda);
        return actoresViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Actores unActor = listaDeActores.get(position);
        ActoresViewHolder actoresViewHolder = (ActoresViewHolder)holder;
        actoresViewHolder.cargarActor(unActor);

        View viewDeLaCelda = actoresViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escuchadorDeActores.seleccionaronA(unActor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeActores.size();
    }

    private class ActoresViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenActor;
        private TextView nombreActor;

        public ActoresViewHolder(View itemView) {
            super(itemView);
            imagenActor = (ImageView)itemView.findViewById(R.id.imageViewActoresCelda);
            nombreActor = (TextView)itemView.findViewById(R.id.textViewActoresCelda);
        }

        public void cargarActor(Actores unActor){
            nombreActor.setText(unActor.getName());
            Glide.with(unContext).load(unActor.getProfile_path()).into(imagenActor);
        }
    }

    public interface EscuchadorDeActores{
        public void seleccionaronA(Actores unActor);
    }
}
