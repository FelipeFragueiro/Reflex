package a0817moact03c_2.a0817moact03c_02.TinderLike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.TinderLike.chat.ChatActivity;

public class MatchesActivity extends AppCompatActivity implements MatchesAdapter.EscuchadorDeMatches {
    private RecyclerView recyclerViewMatch;
    private List<MatchesObject>listaMatches;
    private MatchesAdapter matchesAdapter;
    private String currentUserID;
    private List<PeliculaFavorita>userPhotoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        userPhotoList = new ArrayList<>();

        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();



        recyclerViewMatch = (RecyclerView) findViewById(R.id.recyclerViewMatchesTinder);

        // Toast.makeText(getApplicationContext(),userPhotoList.toString(),Toast.LENGTH_SHORT).show();
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);
        listaMatches = new ArrayList<>();
        //getlist();
        matchesAdapter = new MatchesAdapter(getApplicationContext(),listaMatches,this);
        recyclerViewMatch.setLayoutManager(gridLayoutManager);
        recyclerViewMatch.setAdapter(matchesAdapter);
        
        getUserMatchID();

    }

    private void getUserMatchID() {

        DatabaseReference matchDB = FirebaseDatabase.getInstance().getReference().child("Usuario").child(currentUserID).child("Connections").child("Match");
        matchDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot match : dataSnapshot.getChildren()){
                        FetchMatchInfo(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void FetchMatchInfo(final String key) {
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("Usuario").child(key);
        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    MatchesObject obj = new MatchesObject();
                    String userid = dataSnapshot.getKey();
                    String userName = "";
                    String userPhoto = "";
                    if (dataSnapshot.child("Nombre").getValue() != null){
                        userName = dataSnapshot.child("Nombre").getValue().toString();

                    }
                    if (dataSnapshot.child("Fotos").getValue() != null){
                        userPhoto = dataSnapshot.child("Fotos").getValue().toString();

                    }



                    obj.setUserID(userid);
                    obj.setFoto(userPhoto);
                    obj.setNombre(userName);
                    listaMatches.add(obj);
                    matchesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }






    @Override
    public void seleccionaronA(MatchesObject unaMatch) {
        Intent unIntent = new Intent(MatchesActivity.this, ChatActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("matchID",unaMatch.getUserID().toString());
        bundle.putString("matchName",unaMatch.getNombre());
        bundle.putString("matchFoto",unaMatch.getFoto());

        unIntent.putExtras(bundle);

        startActivity(unIntent);
    }

    //private ArrayList<ChatObject> resultsMatches= new ArrayList<ChatObject>();
   // private List<ChatObject> getDataSetMatches() {
   //     return resultsMatches;
   // }
}
