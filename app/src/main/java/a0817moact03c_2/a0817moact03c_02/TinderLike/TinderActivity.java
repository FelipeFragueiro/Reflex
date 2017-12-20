package a0817moact03c_2.a0817moact03c_02.TinderLike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.Model.Usuario;
import a0817moact03c_2.a0817moact03c_02.R;

public class TinderActivity extends AppCompatActivity {
    //private ArrayList<String> rowItems;
    private List<Usuario> rowItems = new ArrayList<>();
    //private ArrayAdapter<String>arrayAdapter;
    private AdapterUsuarios usuarioAdapterUsuarios;
    private int i;
    private List<PeliculaFavorita> userPhotoList;
    private List<PeliculaFavorita> otraLista;
    ListView listView;


    //lo que podria hacer es que no te mache por las peliculas favoritas si no que directamente se muestren tus peliculas favoritas
    //y ahi decidis si hablar con esa persona o no.
    //no hace falta que no te tire solo mujeres si sos hombre(lo mismo pa mujeres) porque esta app no es para
    //peliCULEAR todavia.
    FirebaseAuth mAuth;
    //mAuth = FirebaseAuth.getInstance();
    //FirebaseUser user = mAuth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        final DatabaseReference databaseReference1 = databaseReference.child("Usuario").child(user.getUid());


       // userPhotoList = new ArrayList<>();


        rowItems = new ArrayList<>();

        getUsuarios();
        usuarioAdapterUsuarios = new AdapterUsuarios(this, R.layout.item,rowItems);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter( usuarioAdapterUsuarios);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                usuarioAdapterUsuarios.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Usuario obj = (Usuario) dataObject;
                String userID = obj.getID();
                databaseReference1.child("Connections").child("No").child(userID).setValue(true);


                makeToast(TinderActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Usuario obj = (Usuario) dataObject;
                String userID = obj.getID();
                databaseReference1.child("Connections").child("Si").child(userID).setValue(true);
                isConnectionMatch(userID);
                makeToast(TinderActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                DatabaseReference pelifavorita = databaseReference.child("Usuario").child("123123").child("Fotos");
                DatabaseReference userid = databaseReference.child("Usuario").child("123123").child("ID");
                DatabaseReference username = databaseReference.child("Usuario").child("123123").child("Nombre");
                DatabaseReference userfav = databaseReference.child("Usuario").child("123123").child("Favoritos").child("pe2");
                DatabaseReference userfav2 = databaseReference.child("Usuario").child("123123").child("Favoritos").child("pe3");
                username.setValue("oh");
                userid.setValue("123123");

                pelifavorita.setValue("http://www.islabit.com/wp-content/uploads/2017/09/Android.png");
                PeliculaFavorita pe2 = new PeliculaFavorita();
                PeliculaFavorita pe3 = new PeliculaFavorita();
                pe3.setTitle("pelicula5");
                pe3.setOverview("asnjdansd");
                pe3.setKey("oipoipoiop");
                pe3.setUserID("123123");

                pe3.setPoster_path("ljn");
                pe2.setTitle("pelicula3");
                pe2.setOverview("asnjdansd");
                pe2.setKey("oipoipoiop");
                pe2.setUserID("123123");
                pe2.setPoster_path("ljn");

                List<PeliculaFavorita> lista = new ArrayList<PeliculaFavorita>();
                userfav.setValue(pe2);
                userfav2.setValue(pe3);
                lista.add(pe2);
                lista.add(pe3);
                Usuario usuario = new Usuario();
                usuario.setNombre("oh".concat(String.valueOf(i)));
                usuario.setFoto("http://www.islabit.com/wp-content/uploads/2017/09/Android.png");
                usuario.setID("123123");
                usuario.setPeliculaFavorita(lista);
                rowItems.add(usuario);
                usuarioAdapterUsuarios.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;*/
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(TinderActivity.this, "Clicked!");
            }
        });

    }

    public void getUsuarios() {
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();


        final String userID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        DatabaseReference pelisfavoritasDB = firebaseDataRef.child("Usuario");
        pelisfavoritasDB.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    final Usuario usuario = new Usuario();
                    userPhotoList = new ArrayList<PeliculaFavorita>();


                        usuario.setID(dataSnapshot.getKey());

                        usuario.setNombre(dataSnapshot.child("Nombre").getValue().toString());

                        usuario.setFoto(dataSnapshot.child("Fotos").getValue().toString());

                        //agregarFavs(usuario.getID());
                        //usuario.setPeliculaFavorita(userPhotoList);




                    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Usuario").child(usuario.getID()).child("Favoritos");
                    firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                                PeliculaFavorita aPeli = snapshotChildren.getValue(PeliculaFavorita.class);


                                    userPhotoList.add(aPeli);

                                 otraLista = new ArrayList<>();



                                //agregarPelicula(userPhotoList,usuario);
                                if (userPhotoList.get(0).getUserID().equals(usuario.getID())){
                                    usuario.setPeliculaFavorita(userPhotoList.get(0));
                                }

                                if (!userPhotoList.isEmpty()){

                                  usuario.setPeliculaFavorita(userPhotoList.get(0));
                                    }


                                rowItems.add(usuario);

                                usuarioAdapterUsuarios.notifyDataSetChanged();


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void agregarPelicula(List<PeliculaFavorita>peli , Usuario usuario){
        for (PeliculaFavorita unaPeli:peli) {
            if (unaPeli.getUserID().equals(usuario.getID()))
                otraLista.add(unaPeli);
            break;
        }





    }

    public void agregarFavs( String usuario){
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Usuario").child(usuario).child("Favoritos");
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    PeliculaFavorita aPeli = snapshotChildren.getValue(PeliculaFavorita.class);

                    userPhotoList.add(aPeli);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public void goToMatches(View view) {
        startActivity(new Intent(TinderActivity.this, MatchesActivity.class));
    }

    private void isConnectionMatch(String userid) {
        final String currentuserid = mAuth.getCurrentUser().getUid();
        DatabaseReference pelisfavoritasDB = databaseReference.child("Usuario").child(currentuserid).child("Connections").child("Si").child(userid);
        pelisfavoritasDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Match", Toast.LENGTH_SHORT).show();
                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();


                    databaseReference.child("Usuario").child(dataSnapshot.getKey()).child("Connections").child("Match").child(currentuserid).child("Chatid").setValue(key);
                    databaseReference.child("Usuario").child(currentuserid).child("Connections").child("Match").child(dataSnapshot.getKey()).child("Chatid").setValue(key);


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

}
