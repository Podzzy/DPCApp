package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.icu.text.Collator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeScreen extends AppCompatActivity {

    Button inboxButton, orderButton, accountButton, logOutButton, createButton;
    FirebaseAuth mAuth;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;
    String userID;
    final String TAG = HomeScreen.class.getSimpleName();
    String currentPhase = null;
    TextView phaseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportActionBar().setTitle("DASHBOARD");

        inboxButton = findViewById(R.id.inboxButton);
        accountButton = findViewById(R.id.accountButton);
        orderButton = findViewById(R.id.orderButton);
        logOutButton = findViewById(R.id.logOutbutton);
        createButton = findViewById(R.id.createButton);
        phaseText = findViewById(R.id.phaseText);

        inboxButton.setVisibility(View.GONE);
        createButton.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase= FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CreateScreen1.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });



        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(view.getContext(), LoginScreen.class));
                Toast.makeText(HomeScreen.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              phaseText.setText(String.valueOf(getCurrentPhase(dataSnapshot)));
                currentPhase = phaseText.getText().toString();
                if(currentPhase.equals("Phase 1")){
                    createButton.setVisibility(View.VISIBLE);
                }else{
                    inboxButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




    }

    private String getCurrentPhase(DataSnapshot dataSnapshot) {
        String cPhase = null;
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            User user = new User();
            user.setPhase(ds.child(userID).getValue(User.class).getPhase());
            cPhase = user.getPhase();
            Log.d(TAG, "showData: phase: " + cPhase);
        }

        return cPhase;

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
    }
}