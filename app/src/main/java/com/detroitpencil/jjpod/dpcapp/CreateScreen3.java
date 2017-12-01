package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CreateScreen3 extends AppCompatActivity {

    EditText phoneText, faxText, cellText;
    String phone = "", fax="", cell="";
    Button nextButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen3);

        getSupportActionBar().setTitle("COMPANY INFO");

        phoneText = findViewById(R.id.phoneText);
        faxText = findViewById(R.id.faxText);
        cellText = findViewById(R.id.cellText);

        nextButton2 = findViewById(R.id.nextButton2);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = phoneText.getText().toString();
                fax = faxText.getText().toString();
                cell = cellText.getText().toString();

                if(phone.equals("") && fax.equals("") && cell.equals("")){
                    Toast.makeText(CreateScreen3.this, "At least one method of contact required.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("phone", phone);
                    editor.putString("fax", fax);
                    editor.putString("cell", cell);
                    editor.commit();

                    Intent j = new Intent(CreateScreen3.this, CreateScreen4.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(j);
                }
            }
        });




    }



}
