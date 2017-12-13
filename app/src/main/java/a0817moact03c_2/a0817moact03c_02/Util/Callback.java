package a0817moact03c_2.a0817moact03c_02.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculasFavoritasRecycler;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;

/**
 * Created by Ines on 11/12/2017.
 */

public class Callback extends ItemTouchHelper.SimpleCallback {

    private AdaptadorDePeliculasFavoritasRecycler adapter; // this will be your recycler adapter

  private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());



    /**
     *
     Make sure you pass in your RecyclerAdapter to this class

     */
    public Callback(int dragDirs, int swipeDirs, AdaptadorDePeliculasFavoritasRecycler adapter, Context applicationContext) {
        super(dragDirs, swipeDirs);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition(); // this is how you can get the position
        PeliculaFavorita object = adapter.getObject(position); // You will have your own

        // class ofcourse.
        List<PeliculaFavorita>favoritaList= new ArrayList<>();
        favoritaList = adapter.getLista();

        for (PeliculaFavorita peliculaFavorita:favoritaList) {
            if (object.getKey().equals(peliculaFavorita.getKey())){
                favoritaList.remove(peliculaFavorita);
                adapter.notifyDataSetChanged();
                break;


            }

        }

        // then you can delete the object

        //root.child(object.getKey()).removeValue();// setting the value to null will just delete it from the database.
        DatabaseReference root2 = FirebaseDatabase.getInstance().getReference().child("Favoritos").child(mAuth.getCurrentUser().getUid()).child(object.getKey());
        root2.removeValue();
    }
}
