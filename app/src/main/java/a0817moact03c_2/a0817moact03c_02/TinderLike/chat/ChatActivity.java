package a0817moact03c_2.a0817moact03c_02.TinderLike.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a0817moact03c_2.a0817moact03c_02.R;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.EscuchadorDeChat {
    private RecyclerView recyclerViewMatch;
    private List<ChatObject> listaMatches;
    private ChatAdapter matchesAdapter;
    private String currentUserID, matchid, chatid;
    private EditText msendEditText;
    private ImageButton mButtonSend;
    DatabaseReference mDatabaseUser, mDatabaseChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        matchid = getIntent().getExtras().getString("matchID");


        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Usuario").child(currentUserID).child("Connections").child("Match").child(matchid).child("Chatid");
        mDatabaseChat = FirebaseDatabase.getInstance().getReference().child("Chat");
        getChatid();


        recyclerViewMatch = (RecyclerView) findViewById(R.id.recyclerViewMatchesTinder);

        // Toast.makeText(getApplicationContext(),userPhotoList.toString(),Toast.LENGTH_SHORT).show();
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);
        listaMatches = new ArrayList<>();
        //getlist();
        matchesAdapter = new ChatAdapter(getApplicationContext(),listaMatches,this);
        recyclerViewMatch.setLayoutManager(gridLayoutManager);
        recyclerViewMatch.setAdapter(matchesAdapter);

        //getUserMatchID();

        msendEditText = findViewById(R.id.edittextenviarmensaje);
        mButtonSend = findViewById(R.id.buttonEnviarChatActivity);
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {
        String sendMessageText = msendEditText.getText().toString();
        if (!sendMessageText.isEmpty()){
            DatabaseReference newMessageDB = mDatabaseChat.push();

            Map newMessage = new HashMap();
            newMessage.put("createdByUser",currentUserID);
            newMessage.put("text",sendMessageText);
            newMessageDB.setValue(newMessage);

        }
        msendEditText.setText(null);

    }
    private void getChatid(){
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    chatid = dataSnapshot.getValue().toString();
                    mDatabaseChat = mDatabaseChat.child(chatid);
                    getChatMessages();
                } else {
                    Toast.makeText(getApplicationContext(),"fk",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getChatMessages() {
        mDatabaseChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    String message = null;
                    String createdbyuser = null;
                    if (dataSnapshot.child("text").getValue() !=null){
                        message = dataSnapshot.child("text").getValue().toString();
                    }
                    if (dataSnapshot.child("createdByUser").getValue() !=null){
                        createdbyuser = dataSnapshot.child("createdByUser").getValue().toString();
                    }
                    if (message != null && createdbyuser != null){
                        Boolean currentuserboolean = false;
                        if(createdbyuser.equals(currentUserID)){
                            currentuserboolean = true;
                        }
                        ChatObject newMessage = new ChatObject();
                        newMessage.setCurrentUser(currentuserboolean);
                        newMessage.setMessage(message);
                        listaMatches.add(newMessage);
                        matchesAdapter.notifyDataSetChanged();
                    }
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

    @Override
    public void seleccionaronA(ChatObject unaMatch) {

    }
}
