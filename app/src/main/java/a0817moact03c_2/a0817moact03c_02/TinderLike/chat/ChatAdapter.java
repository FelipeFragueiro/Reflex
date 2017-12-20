package a0817moact03c_2.a0817moact03c_02.TinderLike.chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.R;

/**
 * Created by Ines on 19/12/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter {


    private List<ChatObject> listaMatches;
    private Context unContexto;
    private EscuchadorDeChat escuchadorDeMatches;

    public ChatAdapter(Context unContexto, List<ChatObject> lista, EscuchadorDeChat escuchadorDeFavoritos) {
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
        View celda = layoutInflater.inflate(R.layout.item_chat, parent, false);
        MatchesViewHolder MatchesViewHolder = new MatchesViewHolder(celda);
        return MatchesViewHolder;
    }

    public ChatObject getObject(int position) {
        ChatObject object = new ChatObject();
        object = listaMatches.get(position);
        return object;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChatObject unMatch = listaMatches.get(position);
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
         //private ImageView imageViewDeMatch;
        private TextView mMessage;
        public LinearLayout mContainer;
        private String hola;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            //imageViewDeMatch = (ImageView) itemView.findViewById(R.id.imageViewMatchImage);
            mMessage = (TextView) itemView.findViewById(R.id.messagetextviewchat);
            mContainer = itemView.findViewById(R.id.containeridchattinder);

            //textViewNombreMatch = (TextView)itemView.findViewById(R.id.textViewMatchesName);
        }

        public void cargarMatch(ChatObject unMatch) {
            mMessage.setText(unMatch.getMessage());
            if (unMatch.getCurrentUser()){
                mMessage.setGravity(Gravity.END);
                mMessage.setTextColor(Color.WHITE);
                //color.parsecolor(#8768)
                mContainer.setBackgroundColor(Color.BLUE);
                mContainer.setGravity(Gravity.RIGHT);

            }else {
                mMessage.setGravity(Gravity.START);
                mMessage.setTextColor(Color.BLACK);
                mContainer.setGravity(Gravity.LEFT);
                //color.parsecolor(#8768)
                mContainer.setBackgroundColor(Color.parseColor("#F4F4F4"));


            }
           // textViewIDMatch.setText(unMatch.getUserID());
            //textViewNombreMatch.setText(unMatch.getNombre());
            //Glide.with(unContexto).load(unMatch.getFoto()).into(imageViewDeMatch);

        }
    }

    public interface EscuchadorDeChat {
        public void seleccionaronA(ChatObject unaMatch);
    }
}

