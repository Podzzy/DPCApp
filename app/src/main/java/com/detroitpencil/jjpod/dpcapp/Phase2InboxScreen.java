package com.detroitpencil.jjpod.dpcapp;

import android.app.LauncherActivity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Phase2InboxScreen extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    FirebaseAuth mAuth;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    TextView emptyText;

    final String TAG = Phase2InboxScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase2_inbox_screen);

        getSupportActionBar().setTitle("INBOX");

        emptyText = findViewById(R.id.emptyText);

        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase= FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        if(arrayList.isEmpty())
            emptyText.setVisibility(View.VISIBLE);



        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        listView.setAdapter(adapter);

        mRef.child("phase2inbox").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                   String cName =  ds.getKey();
                   String date = (String) ds.child(cName).child("submitDate").getValue();
                   Toast.makeText(Phase2InboxScreen.this, date, Toast.LENGTH_SHORT).show();

                   arrayList.add(cName);
                   emptyText.setVisibility(View.GONE);

                   adapter.notifyDataSetChanged();
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
                String companyName = listView.getItemAtPosition(i).toString();
                Intent intent = new Intent(Phase2InboxScreen.this, ReviewP1InfoScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("inboxCompany", companyName);
                startActivity(intent);
            }
        });


    }

}
