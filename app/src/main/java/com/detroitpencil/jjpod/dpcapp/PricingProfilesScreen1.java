package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class PricingProfilesScreen1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String paperBrand, tonerBrand, matrix, USAExpress, inboxCompany;

    String[] paperBrands, tonerBrands, matrixItems;

    Spinner paperSpinner, tonerSpinner, matrixSpinner;

    RadioButton USAExpressYes, USAExpressNo;

    Button nextScreenButton2;

    final String TAG = PricingProfilesScreen1.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing_profiles_screen1);

        getSupportActionBar().setTitle("PRICING PROFILE");

        Intent j = getIntent();
        inboxCompany = j.getStringExtra("inboxCompany");

        paperSpinner = findViewById(R.id.paperSpinner);
        tonerSpinner = findViewById(R.id.tonerSpinner);
        matrixSpinner = findViewById(R.id.matrixSpinner);

        paperSpinner.setOnItemSelectedListener(this);
        paperBrands = new  String[]{"P8", "P10", "P14", "P17", "P20"};
        ArrayAdapter<String> paperAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paperBrands);
        paperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paperSpinner.setAdapter(paperAdapter);

        tonerSpinner.setOnItemSelectedListener(this);
        tonerBrands = new  String[]{"20A-5", "20A-10", "20A+5", "20A7GP"};
        ArrayAdapter<String> tonerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tonerBrands);
        paperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tonerSpinner.setAdapter(tonerAdapter);

        matrixSpinner.setOnItemSelectedListener(this);
        matrixItems = new  String[]{"2015", "2016", "20A", "20C", "50A", "38S"};
        ArrayAdapter<String>matrixAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, matrixItems);
        matrixAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrixSpinner.setAdapter(matrixAdapter);

        USAExpressYes = findViewById(R.id.USAExpressYes);
        USAExpressNo = findViewById(R.id.USAExpressNO);

        nextScreenButton2 = findViewById(R.id.nextScreenButton2);

        nextScreenButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(USAExpress == null){
                    Toast.makeText(PricingProfilesScreen1.this, "Must choose a USA/Express option.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("paperBrand", paperBrand);
                editor.putString("tonerBrand", tonerBrand);
                editor.putString("matrix", matrix);
                editor.putString("USAExpress", USAExpress);
                editor.putString("inboxCompany", inboxCompany);
                editor.apply();

                Log.w(TAG, "Paper: "+paperBrand);
                Log.w(TAG, "Toner: "+tonerBrand);
                Log.w(TAG, "Matrix: "+matrix);
                Log.w(TAG, "USAExpress: "+USAExpress);

                if(USAExpress.equals("")){
                    Toast.makeText(PricingProfilesScreen1.this, "USAExpress option required.", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent j = new Intent(view.getContext(), PricingProfilesScreen2.class);
                j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(j);
            }
        });


    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.USAExpressYes:
                if(checked)
                    USAExpress = "yes";
                break;
            case R.id.USAExpressNO:
                if(checked)
                    USAExpress= "no";
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.paperSpinner:
                switch(i){
                    case 0:
                        paperBrand = "P8";
                        break;
                    case 1:
                        paperBrand = "P10";
                        break;
                    case 2:
                        paperBrand = "P14";
                        break;
                    case 3:
                        paperBrand = "P17";
                        break;
                    case 4:
                        paperBrand = "P20";
                        break;
                    default:
                        break;
                }
                break;
            case R.id.tonerSpinner:
                switch(i){
                    case 0:
                        tonerBrand = "20A-5";
                        break;
                    case 1:
                        tonerBrand = "20A-10";
                        break;
                    case 2:
                        tonerBrand = "20A+5";
                        break;
                    case 3:
                        tonerBrand = "20A7GP";
                        break;
                    default:
                        break;
                }
                break;
            case R.id.matrixSpinner:
                switch(i){
                    case 0:
                        matrix = "2015";
                        break;
                    case 1:
                        matrix = "2016";
                        break;
                    case 2:
                        matrix = "20A";
                        break;
                    case 3:
                        matrix = "20C";
                        break;
                    case 4:
                        matrix = "50A";
                        break;
                    case 5:
                        matrix = "38S";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
