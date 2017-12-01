package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AdditionalInfoScreen extends AppCompatActivity {
    EditText locationText, wcwText, bsnVersionText;
    String boxOption = "", bsnOption="", wcw ="", iOption="", bsnVersion = "", location="";
    Button rNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info_screen);

        getSupportActionBar().setTitle("ADDITIONAL CUSTOMER INFO");

        locationText = findViewById(R.id.locationText);
        wcwText = findViewById(R.id.wcwText);
        bsnVersionText = findViewById(R.id.bsnVersionText);

        rNextButton = findViewById(R.id.rNextButton);
        rNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                location = locationText.getText().toString();
                wcw = wcwText.getText().toString();
                bsnVersion   = bsnVersionText.getText().toString();

                if(boxOption.equals("")){
                    Toast.makeText(AdditionalInfoScreen.this, "Box program selection required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(bsnOption.equals("")){
                    Toast.makeText(AdditionalInfoScreen.this, "BSN selection required.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(iOption.equals("")){
                    Toast.makeText(AdditionalInfoScreen.this, "Internet set up selection required", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("location", location);
                    editor.putString("wcw", wcw);
                    editor.putString("bsnVersion", bsnVersion);
                    editor.putString("boxOption", boxOption);
                    editor.putString("bsnOption", bsnOption);
                    editor.putString("iOption", iOption);
                    editor.commit();

                    Intent j = new Intent(AdditionalInfoScreen.this, ReviewScreen.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(j);
                }
            }
        });

    }

    public void onRadioButtonClickedBox(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.boxYes:
                if(checked)
                    boxOption = "yes";
                break;
            case R.id.boxNo:
                if(checked)
                    boxOption = "no";
                break;
            default:
                break;

        }
    }

    public void onRadioButtonClickedBSN(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.bsnYes:
                if(checked){
                    bsnOption = "yes";
                    bsnVersionText.setEnabled(true);
                }
                break;
            case R.id.bsnNo:
                if(checked){
                    bsnOption = "no";
                    bsnVersionText.setEnabled(false);
                }
                break;
            default:
                break;

        }
    }

    public void onRadioButtonClickedInternet(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.iMaster:
                if(checked)
                   iOption = "MasterUser";
                break;
            case R.id.iApproval:
                if(checked)
                    iOption = "Approval Routing";
                break;
            default:
                break;

        }
    }
}
