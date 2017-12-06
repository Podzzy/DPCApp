package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PricingProfilesScreen2 extends AppCompatActivity {

    String costPlus, customPrice, pricingNotes, paperBrand, tonerBrand, matrix, USAExpress, inboxCompany, submitDate;
    final String TAG = PricingProfilesScreen2.class.getSimpleName();

    EditText costPlusText, customPriceText, pricingNotesText;

    Button submitPricingButton;

    DatabaseReference fromRef, toRef, myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing_profiles_screen2);

        getSupportActionBar().setTitle("PRICING PROFILE");

        costPlusText = findViewById(R.id.costPlusText);
        customPriceText = findViewById(R.id.customPricetext);
        pricingNotesText = findViewById(R.id.pricingNotesText);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        paperBrand = prefs.getString("paperBrand", "Error");
        tonerBrand = prefs.getString("tonerBrand", "Error");
        matrix = prefs.getString("matrix", "Error");
        USAExpress = prefs.getString("USAExpress", "Error");
        inboxCompany = prefs.getString("inboxCompany", "Error");

        fromRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dpcapp-jjpod.firebaseio.com/phase2inbox/");
        toRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dpcapp-jjpod.firebaseio.com/phase3inbox/");

        submitPricingButton = findViewById(R.id.submitPricingButton);
        submitPricingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costPlus = costPlusText.getText().toString();
                customPrice = customPriceText.getText().toString();
                pricingNotes = pricingNotesText.getText().toString();


                Intent i = new Intent(PricingProfilesScreen2.this, HomeScreen.class);
                Toast.makeText(PricingProfilesScreen2.this, "Phase 2 info submitted successfully.", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                Calendar c = Calendar.getInstance();
                SimpleDateFormat dformat = new SimpleDateFormat("MM/dd/yyyy");
                submitDate = dformat.format(c.getTime());

                //Add all new info to existing phase 1 info before moving its location
                fromRef.child(inboxCompany).child("submitDate").setValue(submitDate);
                fromRef.child(inboxCompany).child("costPlus").setValue(costPlus);
                fromRef.child(inboxCompany).child("customPrice").setValue(customPrice);
                fromRef.child(inboxCompany).child("pricingNotes").setValue(pricingNotes);
                fromRef.child(inboxCompany).child("paperBrand").setValue(paperBrand);
                fromRef.child(inboxCompany).child("tonerBrand").setValue(tonerBrand);
                fromRef.child(inboxCompany).child("matrix").setValue(matrix);
                fromRef.child(inboxCompany).child("usaExpress").setValue(USAExpress);
                moveFirebaseRecord(fromRef, toRef, inboxCompany);


                fromRef.child(inboxCompany).removeValue();

            }
        });
    }

    public void moveFirebaseRecord(final DatabaseReference fromPath, final DatabaseReference toPath, final String key)
    {
        if(fromPath.child(key) == null || toPath.child(key)==null){
            //.makeText(this, "Path is null.", Toast.LENGTH_SHORT).show();
            return;
        }
        fromPath.child(key).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                toPath.child(dataSnapshot.getKey()).setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener()
                {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase)
                    {
                        if (firebaseError != null)
                        {
                            Log.w(TAG, "onComplete: SUCCESS");
                        }
                        else
                        {
                            Log.w(TAG, "onComplete: ERROR");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError firebaseError)
            {
                System.out.println("Copy failed");
            }
        });
    }


}
