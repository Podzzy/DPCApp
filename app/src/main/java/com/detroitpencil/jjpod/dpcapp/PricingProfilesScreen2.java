package com.detroitpencil.jjpod.dpcapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PricingProfilesScreen2 extends AppCompatActivity {

    String costPlus, customPrice, pricingNotes, paperBrand, tonerBrand, matrix, USAExpress;

    EditText costPlusText, customPriceText, pricingNotesText;

    Button submitPricingButton;

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

        submitPricingButton = findViewById(R.id.submitPricingButton);
        submitPricingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costPlus = costPlusText.getText().toString();
                customPrice = customPriceText.getText().toString();
                pricingNotes = pricingNotesText.getText().toString();


            }
        });


    }
}
