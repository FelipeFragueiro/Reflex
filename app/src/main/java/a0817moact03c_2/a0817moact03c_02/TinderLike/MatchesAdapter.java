package a0817moact03c_2.a0817moact03c_02.TinderLike;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by Ines on 19/12/2017.
 */

public class MatchesAdapter extends RecyclerView.Adapter {


    private List<MatchesObject> listaMatches;
    private Context unContexto;
    private EscuchadorDeMatches escuchadorDeMatches;

    public MatchesAdapter(Context unContexto, List<MatchesObject> lista, EscuchadorDeMatches escuchadorDeFavoritos) {
        this.listaMatches = lista;
        this.unContexto = unContexto;
        this.escuchadorDeMatches = escuchadorDeFavoritos;
    }

    //Crea la celda
    //public List<PeliculaFavorita> getLista(){
    //    return this.listaDePeliculasfavoritas;
    //}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(unContexto);
        View celda = layoutInflater.inflate(R.layout.item_matches, parent, false);
        MatchesViewHolder MatchesViewHolder = new MatchesViewHolder(celda);
        return MatchesViewHolder;
    }

    public MatchesObject getObject(int position) {
        MatchesObject object = new MatchesObject();
        object = listaMatches.get(position);
        return object;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MatchesObject unMatch = listaMatches.get(position);
        MatchesViewHolder matchesViewHolder = (MatchesViewHolder) holder;
        matchesViewHolder.cargarMatch(unMatch);

        View viewDeLaCelda = matchesViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aca va el coidog que quiero que ejecute cuando se selecciona una celda
                escuchadorDeMatches.seleccionaronA((unMatch));
                //Escuchador.Seleccionaron a (unaPelicula)

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMatches.size();
    }


    private class MatchesViewHolder extends RecyclerView.ViewHolder {
         private ImageView imageViewDeMatch;
        private TextView textViewIDMatch;
        private TextView textViewNombreMatch;


        public MatchesViewHolder(View itemView) {
            super(itemView);
            imageViewDeMatch = (ImageView) itemView.findViewById(R.id.imageViewMatchImage);
            textViewIDMatch = (TextView) itemView.findViewById(R.id.textViewMatchesID);
            textViewNombreMatch = (TextView)itemView.findViewById(R.id.textViewMatchesName);
        }

        public void cargarMatch(MatchesObject unMatch) {
            textViewIDMatch.setText(unMatch.getUserID());
            textViewNombreMatch.setText(unMatch.getNombre());
            Glide.with(unContexto).load(unMatch.getFoto()).into(imageViewDeMatch);

        }
    }

    public interface EscuchadorDeMatches {
        public void seleccionaronA(MatchesObject unaMatch);
    }
}

