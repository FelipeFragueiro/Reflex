package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.AdaptadorDePeliculasFavoritasRecycler;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.Callback;

public class FavoritosActivity extends AppCompatActivity implements AdaptadorDePeliculasFavoritasRecycler.EscuchadorDeFavoritos{
    private RecyclerView recyclerViewCollage;
    private List<PeliculaFavorita> userPhotoList = new ArrayList<>();
    private AdaptadorDePeliculasFavoritasRecycler.EscuchadorDeFavoritos unEscuchador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        cargarFotos();





    }
    public void cargarFotos() {
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        DatabaseReference pelisfavoritasDB = firebaseDataRef.child("Favoritos").child(userID);

        pelisfavoritasDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    PeliculaFavorita aPeli = snapshotChildren.getValue(PeliculaFavorita.class);
                   if (contiene(aPeli)){
                       userPhotoList.add(aPeli);
                   }
                }
                recyclerViewCollage = (RecyclerView) findViewById(R.id.recyclerPeliculasYSeriesFavoritas);
                AdaptadorDePeliculasFavoritasRecycler paintCollageAdapter = new AdaptadorDePeliculasFavoritasRecycler(getApplicationContext(), userPhotoList);
                // Toast.makeText(getApplicationContext(),userPhotoList.toString(),Toast.LENGTH_SHORT).show();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);
                recyclerViewCollage.setLayoutManager(gridLayoutManager);
                recyclerViewCollage.setAdapter(paintCollageAdapter);

                ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new
                        Callback(0, ItemTouchHelper.LEFT, paintCollageAdapter,getApplicationContext()); // Making the SimpleCallback

                ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);

                touchHelper.attachToRecyclerView(recyclerViewCollage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FavoritosActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean contiene(PeliculaFavorita unaPeli){
        for (PeliculaFavorita unaPeli1:userPhotoList) {
            if (unaPeli.getTitle().equals(unaPeli1.getTitle())){

                return false;
            }
        }
        return true;
    }

    @Override
    public void seleccionaronA(PeliculaFavorita unaPelicula) {

    }

}
