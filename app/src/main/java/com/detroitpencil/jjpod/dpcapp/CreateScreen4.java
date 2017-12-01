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

public class CreateScreen4 extends AppCompatActivity {

    String  apContactName="", apPhone="", apCell="", apOther="", apEmail="", invoices="";
    EditText apContactText, apPhoneText, apCellText, apOtherText, apEmailText;
    Button nextButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen4);

        getSupportActionBar().setTitle("PRIMARY AP INFO");

        apContactText = findViewById(R.id.apContactText);
        apPhoneText = findViewById(R.id.apPhoneText);
        apCellText = findViewById(R.id.apCellText);
        apOtherText = findViewById(R.id. apOtherText);
        apEmailText = findViewById(R.id.apEmailText);

        nextButton3 = findViewById(R.id.nextButton3);
        nextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apContactName = apContactText.getText().toString();
                apPhone = apPhoneText.getText().toString();
                apCell = apCellText.getText().toString();
                apOther = apOtherText.getText().toString();
                apEmail = apEmailText.getText().toString();

                if(apContactName.equals("")){
                    Toast.makeText(CreateScreen4.this, "Contact name required.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(apPhone.equals("") && apCell.equals("") && apOther.equals("") && apEmail.equals("")){
                    Toast.makeText(CreateScreen4.this, "At least one method of contact is required.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(invoices.equals("")){
                    Toast.makeText(CreateScreen4.this, "Invoice option requires selection.", Toast.LENGTH_SHORT).show();
                }else{

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("apContactName", apContactName);
                    editor.putString("apPhone", apPhone);
                    editor.putString("apCell", apCell);
                    editor.putString("apOther", apOther);
                    editor.putString("apEmail", apEmail);
                    editor.putString("invoices",invoices);
                    editor.commit();

                    Intent j = new Intent(CreateScreen4.this, CreateScreen5.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    
                    startActivity(j);

                }
            }
        });
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.radioButtonYes:
                if(checked)
                    invoices = "yes";
                break;
            case R.id.radioButtonNo:
                if(checked)
                    invoices = "no";
                break;
            default:
                break;

        }
    }
}
