package a0817moact03c_2.a0817moact03c_02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ma on 26/10/17.
 */

public class AdaptadorGenerosRecycler extends RecyclerView.Adapter {
    private List<Genero> generosAMostrar;
    private Context unContexto;
    private GenerosListener generosListener;

    public AdaptadorGenerosRecycler(List<Genero> generosAMostrar, Context unContexto,GenerosListener unlistener  ){
        this.generosAMostrar = generosAMostrar;
        this.unContexto = unContexto;
        this.generosListener = unlistener;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celdaGenero = layoutInflater.inflate(R.layout.celda_genero,parent, false);
        GeneroViewHolder generoViewHolder = new GeneroViewHolder(celdaGenero);

        return generoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Genero unGenero = generosAMostrar.get(position);
        GeneroViewHolder generoViewHolder = (GeneroViewHolder) holder;
        generoViewHolder.cargarGenero(unGenero);
        View viewDeLaCelda = generoViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aca va el codigo que quiero que ejecute cuando se selecciona una celda
                generosListener.seleccionaronA(unGenero);
            }
        });
    }

    @Override
    public int getItemCount() {
        return generosAMostrar.size();
    }

    private class GeneroViewHolder extends RecyclerView.ViewHolder{
        private TextView textviewNombeGenero;
        private ImageView imageViewGenero;

        public GeneroViewHolder(View itemView) {
            super(itemView);
            textviewNombeGenero = (TextView)itemView.findViewById(R.id.TextviewGenero);
            imageViewGenero = (ImageView)itemView.findViewById(R.id.ImageviewGenero);
        }
        public  void cargarGenero(Genero unGenero){
            textviewNombeGenero.setText(unGenero.getNombre());
            imageViewGenero.setImageResource(unGenero.getImagen());
        }
    }
    public interface GenerosListener{
        public void seleccionaronA(Genero unGenero);
    }
}
