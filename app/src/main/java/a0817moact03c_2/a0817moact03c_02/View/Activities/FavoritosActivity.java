package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
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

import a0817moact03c_2.a0817moact03c_02.View.Adapters.AdaptadorDePeliculasFavoritasRecycler;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.Util.Callback;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

public class FavoritosActivity extends AppCompatActivity implements AdaptadorDePeliculasFavoritasRecycler.EscuchadorDeFavoritos{
    private RecyclerView recyclerViewCollage;
    private List<PeliculaFavorita> userPhotoList = new ArrayList<>();
    private AdaptadorDePeliculasFavoritasRecycler paintCollageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        paintCollageAdapter =  new AdaptadorDePeliculasFavoritasRecycler(getApplicationContext(), userPhotoList,this);
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

                // Toast.makeText(getApplicationContext(),userPhotoList.toString(),Toast.LENGTH_SHORT).show();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);
                recyclerViewCollage.setLayoutManager(gridLayoutManager);
                recyclerViewCollage.setAdapter((RecyclerView.Adapter) paintCollageAdapter);

                ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new
                        Callback(0, ItemTouchHelper.LEFT,  paintCollageAdapter,getApplicationContext()); // Making the SimpleCallback

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
private String pelicula = "pelicula";
    @Override
    public void seleccionaronA(PeliculaFavorita unaPelicula) {
        if(unaPelicula.getSerieOpeli().equals(pelicula)) {
            Toast.makeText(getApplicationContext(), unaPelicula.getTitle(), Toast.LENGTH_SHORT).show();
            Intent unIntent = new Intent(this, DetallePeliculaActivity.class);
            Bundle unBundle = new Bundle();
            unBundle.putString(DetallePeliculaFragment.NOMBRE_PELICULA, unaPelicula.getTitle());
            unBundle.putInt(DetallePeliculaFragment.POSICION_PELICULA, unaPelicula.getPosicion());
            unBundle.putString(DetallePeliculaFragment.ID_PELICULA, unaPelicula.getId());
            //unBundle.putString(DetallePeliculaFragment.GENERO_PELICULA,unaPelicula.getGenre_ids());
            unBundle.putString(DetallePeliculaFragment.DESCRIPCION_PELICULA, unaPelicula.getOverview());
            unBundle.putString(DetallePeliculaFragment.IMAGEN_PELICULA, unaPelicula.getPoster_path());
            unBundle.putString(DetallePeliculaFragment.FECHAS_ESTRENO_PELICULA, unaPelicula.getRelease_date());
            unIntent.putExtras(unBundle);
            startActivity(unIntent);
        }else {
            Intent unIntent = new Intent(this, DetalleSeriesActivity.class);
            Bundle unBundle = new Bundle();
            unBundle.putString("nombre_serie",unaPelicula.getTitle());
            //unBundle.putString("genero_serie",unaSerie.getGenre_ids());
            unBundle.putString("descripcion_serie",unaPelicula.getOverview());
            unBundle.putString("imagen_serie",unaPelicula.getPoster_path());
            unBundle.putInt("posicion_serie",unaPelicula.getPosicion());
            unBundle.putString("idDeSerie",unaPelicula.getId());
            unIntent.putExtras(unBundle);
            startActivity(unIntent);

        }

        //esto nos da alto errror lo que se me ocurrio fue si es una pelicula, ponerle como valor "serie" si es una serie y "pelicula" si es una pelicula
        //entonces hacemos un if en el escuchador y asi vemos que metodo usar con cada peli/serie
        //dudo que funcione pero probemos ehjesasdjm
    }

}
