package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateScreen1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText salesNameText, salesIDText;
    Spinner companySpinner;
    Button nextPageButton;
    String [] companies;
    int position;
    String company = null ,salesName = null, salesID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen1);

        getSupportActionBar().setTitle("SALSEPERSON INFORMATION");

        salesNameText = findViewById(R.id.salesNameText);
        salesIDText = findViewById(R.id.salesIDText);

        companySpinner = findViewById(R.id.companySpinner);
        companySpinner.setOnItemSelectedListener(this);
        companies = new  String[]{"Detroit Pencil Company", "Supply Geeks", "FRIS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(adapter);

        nextPageButton = findViewById(R.id.nextPageButton);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CreateScreen2.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                salesName = salesNameText.getText().toString();
                salesID = salesIDText.getText().toString();
                if(salesName.equals("") || salesID.equals("") || company.equals("")){
                    Toast.makeText(view.getContext(), "All fields must be filled.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    startActivity(i);
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        salesNameText.setText(salesName);
        salesIDText.setText(salesID);
        companySpinner.setSelection(position);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                company = "Detroit Pencil Company";
                position = i;
                break;
            case 1:
                company = "Supply Geeks";
                position = i;
                break;
            case 2:
                company = "FRIS";
                position = i;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        company = null;
        Toast.makeText(this, "Please choose a company.", Toast.LENGTH_SHORT).show();
    }
}
