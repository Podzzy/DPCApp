package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Collator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateScreen5 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] paymentOptions;
    Spinner paymentSpinner;
    EditText notesText;
    String poOption = "", payment="", notes = "", taxable="";
    Button nextButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen5);

        getSupportActionBar().setTitle("ADDITIONAL INFO");

        paymentSpinner = findViewById(R.id.paymentSpinner);
        paymentSpinner.setOnItemSelectedListener(this);
        paymentOptions = new  String[]{"Net 30", "Credit Card", "ACH"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(adapter);

        notesText = findViewById(R.id.notesText);

        nextButton4 = findViewById(R.id.nextButton4);
        nextButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notes = notesText.getText().toString();
                if(poOption.equals("")){
                    Toast.makeText(CreateScreen5.this, "P.O. selection required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(payment.equals("")){
                    Toast.makeText(CreateScreen5.this, "Payment selection required.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(taxable.equals("")){
                    Toast.makeText(CreateScreen5.this, "Taxable selection required", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("poOption", poOption);
                    editor.putString("payment", payment);
                    editor.putString("taxable", taxable);
                    editor.putString("notes", notes);
                    editor.commit();

                    Intent j = new Intent(CreateScreen5.this, DeliveryCheckScreen.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(j);
                }
            }
        });

    }

    public void onRadioButtonClickedPO(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.poYes:
                if(checked)
                    poOption = "yes";
                break;
            case R.id.poNo:
                if(checked)
                    poOption = "no";
                break;
            default:
                break;

        }
    }

    public void onRadioButtonClickedTax(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.tYes:
                if(checked)
                    taxable = "yes";
                break;
            case R.id.tNo:
                if(checked)
                    taxable = "no";
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                payment = "Net 30";
                break;
            case 1:
                payment = "Credit Card";
                break;
            case 2:
                payment = "ACH";
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
