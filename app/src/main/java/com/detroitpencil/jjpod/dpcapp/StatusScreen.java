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

public class StatusScreen extends AppCompatActivity {


    ListView listView2, listView3, listView4;
    //ArrayAdapter<String> adapter;
    //ArrayList<String> arrayList;
    InboxAdapter adapter2, adapter3, adapter4 ;

    FirebaseAuth mAuth;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    TextView emptyTextS1, emptyTextS2,emptyTextComplete;

    final String TAG = Phase2InboxScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_screen);

        getSupportActionBar().setTitle("ACCOUNT STATUS");

        emptyTextS1 = findViewById(R.id.emptyTextS1);
        emptyTextS2 = findViewById(R.id.emptyTextS2);
        emptyTextComplete = findViewById(R.id.emptyTextComplete);
        emptyTextS1.setVisibility(View.GONE);
        emptyTextS2.setVisibility(View.GONE);
        emptyTextComplete.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase= FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference();

        listView2 = findViewById(R.id.listView2);
        listView3 = findViewById(R.id.listView3);
        listView4 = findViewById(R.id.listView4);
        //arrayList = new ArrayList<String>();



        adapter2 = new InboxAdapter("s1");
        adapter3 = new InboxAdapter("s2");
        adapter4 = new InboxAdapter("complete");

        if(adapter2.isEmpty())
            emptyTextS1.setVisibility(View.VISIBLE);
        if(adapter3.isEmpty())
            emptyTextS2.setVisibility(View.VISIBLE);
        if(adapter4.isEmpty())
            emptyTextComplete.setVisibility(View.VISIBLE);

        mRef.child("phase2inbox").addValueEventListener(new ValueEventListener() {
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

                    adapter2.addInboxItem(cName, date);
                    emptyTextS1.setVisibility(View.GONE);

                    listView2.setAdapter(adapter2);
                    //iAdapter.notifyDataSetChanged();
                    Log.w(TAG, cName);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

                    adapter3.addInboxItem(cName, date);
                    emptyTextS2.setVisibility(View.GONE);

                    listView3.setAdapter(adapter3);
                    Log.w(TAG, cName);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InboxItem inbox = (InboxItem) adapter2.getItem(i);
                String companyName = inbox.getCompanyName();
                listView2.setAdapter(adapter2);
                Intent intent = new Intent(StatusScreen.this, ReviewP1InfoScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("inboxCompany", companyName);
                intent.putExtra("status","yes");
                startActivity(intent);
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InboxItem inbox = (InboxItem) adapter3.getItem(i);
                String companyName = inbox.getCompanyName();
                listView3.setAdapter(adapter3);
                Intent intent = new Intent(StatusScreen.this, ReviewP2InfoScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("inboxCompany", companyName);
                intent.putExtra("status", "yes");
                startActivity(intent);
            }
        });


    }
}
