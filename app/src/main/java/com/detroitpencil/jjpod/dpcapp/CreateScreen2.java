package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateScreen2 extends AppCompatActivity {

    EditText companyText, address1Text, address2Text, cityText, stateText, zipText;
    String companyName, address1, address2, city, state, zip;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen2);

        getSupportActionBar().setTitle("COMPANY INFORMATION");

        companyText = findViewById(R.id.companyText);
        address1Text = findViewById(R.id.address1Text);
        address2Text = findViewById(R.id.address2Text);
        cityText = findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        zipText = findViewById(R.id.zipText);



        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyName = companyText.getText().toString();
                address1 = address1Text.getText().toString();
                address2 = address2Text.getText().toString();
                city = cityText.getText().toString();
                state = stateText.getText().toString();
                zip = zipText.getText().toString();

                if(companyName.equals("")|| address1.equals("")||city.equals("")||state.equals("")||zip.equals("")){
                    Toast.makeText(CreateScreen2.this, "Some fields are missing.", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    startActivity(new Intent(CreateScreen2.this, CreateScreen3.class));
                }


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        companyText.setText(companyName);
        address1Text.setText(address1);
        address2Text.setText(address2);
        cityText.setText(city);
        stateText.setText(state);
        zipText.setText(zip);
    }
}
