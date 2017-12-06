package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Phase3InboxScreen extends AppCompatActivity {

    ListView listView;
    InboxAdapter iAdapter ;

    FirebaseAuth mAuth;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    TextView emptyText;

    final String TAG = Phase2InboxScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase3_inbox_screen);

        getSupportActionBar().setTitle("INBOX");

        emptyText = findViewById(R.id.emptyText1);

        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase= FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();

        listView = findViewById(R.id.listView1);



        iAdapter = new InboxAdapter();

       if(iAdapter.isEmpty())
            emptyText.setVisibility(View.VISIBLE);

        mRef.child("phase3inbox").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String cName =  ds.getKey();
                    Phase1Info phase1Info;
                    phase1Info = ds.getValue(Phase1Info.class);
                    String date = null;
                    if (phase1Info != null) {
                        date = phase1Info.getSubmitDate();
                    }
                    Log.w(TAG, "SUBMIT DATE:"+date);

                    iAdapter.addInboxItem(cName, date);
                    emptyText.setVisibility(View.GONE);

                    listView.setAdapter(iAdapter);
                    Log.w(TAG, cName);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InboxItem inbox = (InboxItem) iAdapter.getItem(i);
                String companyName = inbox.getCompanyName();
                listView.setAdapter(iAdapter);
                Intent intent = new Intent(Phase3InboxScreen.this, ReviewP2InfoScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("inboxCompany", companyName);
                startActivity(intent);
            }
        });


    }
}
