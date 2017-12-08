package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryCheckScreen extends AppCompatActivity {

    Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_check_screen);

        getSupportActionBar().setTitle("DELIVERY INFO");

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("deliveryCheckOption", "yes");
                editor.commit();

                startActivity(new Intent(view.getContext(), AdditionalInfoScreen.class));
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("deliveryCheckOption", "no");
                editor.commit();

                Intent i = new Intent(view.getContext(), DeliveryScreen1.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });


    }
}
