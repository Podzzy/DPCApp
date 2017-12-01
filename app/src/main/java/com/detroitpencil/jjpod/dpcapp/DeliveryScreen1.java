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

public class DeliveryScreen1 extends AppCompatActivity {

    EditText  dAddress1Text, dAddress2Text, dCityText, dStateText, dZipText;
    String  dAddress1, dAddress2= "", dCity, dState, dZip;
    Button dNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_screen1);

        getSupportActionBar().setTitle("DELIVERY INFO");

        dAddress1Text = findViewById(R.id.dAddress1Text);
        dAddress2Text = findViewById(R.id.dAddress2Text);
        dCityText = findViewById(R.id.dCityText);
        dStateText = findViewById(R.id.dStateText);
        dZipText = findViewById(R.id.dZipText);



        dNextButton = findViewById(R.id.dNextButton);
        dNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dAddress1 = dAddress1Text.getText().toString();
                dAddress2 = dAddress2Text.getText().toString();
                dCity = dCityText.getText().toString();
                dState = dStateText.getText().toString();
                dZip = dZipText.getText().toString();

                if(dAddress1.equals("")||dCity.equals("")||dState.equals("")||dZip.equals("")){
                    Toast.makeText(DeliveryScreen1.this, "Some fields are missing.", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("dAddress1", dAddress1);
                    editor.putString("dAddress2", dAddress2);
                    editor.putString("dCity", dCity);
                    editor.putString("dState", dState);
                    editor.putString("dZip", dZip);
                    editor.commit();

                    Intent j = new Intent(DeliveryScreen1.this, DeliveryScreen2.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(j);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dAddress1Text.setText(dAddress1);
        dAddress2Text.setText(dAddress2);
        dCityText.setText(dCity);
        dStateText.setText(dState);
        dZipText.setText(dZip);
    }
}

