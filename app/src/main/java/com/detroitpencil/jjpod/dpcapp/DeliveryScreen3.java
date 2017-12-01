package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeliveryScreen3 extends AppCompatActivity {
    String dBuyer ="", dBuyerPhone ="", dBuyerCell ="", dBuyerOther ="", dNotes = "";
    EditText dBuyerText, dBuyerPhoneText, dBuyerCellText, dBuyerOtherText, dNotesText;
    Button dNextButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_screen3);

        getSupportActionBar().setTitle("PRIMARY BUYER INFO");

        dBuyerText = findViewById(R.id.dBuyerText);
        dBuyerPhoneText = findViewById(R.id.dBuyerPhoneText);
        dBuyerCellText = findViewById(R.id.dBuyerCellText);
        dBuyerOtherText = findViewById(R.id.dBuyerOtherText);
        dNotesText = findViewById(R.id.dNotesText);


        dNextButton3 = findViewById(R.id.dNextButton3);
        dNextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBuyer = dBuyerText.getText().toString();
                dBuyerPhone = dBuyerPhoneText.getText().toString();
                dBuyerCell = dBuyerCellText.getText().toString();
                dBuyerOther = dBuyerOtherText.getText().toString();
                dNotes = dNotesText.getText().toString();


                if(dBuyer.equals("")){
                    Toast.makeText(DeliveryScreen3.this, "Contact name required.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(dBuyerPhone.equals("") && dBuyerCell.equals("") && dBuyerOther.equals("")){
                    Toast.makeText(DeliveryScreen3.this, "At least one method of contact is required.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("dBuyer", dBuyer);
                    editor.putString("dBuyerPhone", dBuyerPhone);
                    editor.putString("dBuyerCell", dBuyerCell);
                    editor.putString("dBuyerOther", dBuyerOther);
                    editor.putString("dNotes", dNotes);
                    editor.commit();

                    Intent j = new Intent(DeliveryScreen3.this, AdditionalInfoScreen.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    startActivity(j);

                }
            }
        });
    }

}