package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeliveryScreen2 extends AppCompatActivity {

    EditText dPhoneText, dFaxText, dCellText;
    String dPhone = "", dFax = "", dCell = "";
    Button dNextButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_screen2);

        getSupportActionBar().setTitle("DELIVERY INFO");

        dPhoneText = findViewById(R.id.dPhoneText);
        dFaxText = findViewById(R.id.dFaxText);
        dCellText = findViewById(R.id.dCellText);

        dNextButton2 = findViewById(R.id.dNextButton2);
        dNextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dPhone = dPhoneText.getText().toString();
                dFax = dFaxText.getText().toString();
                dCell = dCellText.getText().toString();

                if (dPhone.equals("") && dFax.equals("") && dCell.equals("")) {
                    Toast.makeText(DeliveryScreen2.this, "At least one method of contact required.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent i = new Intent();
                    i.setAction("com.detroitpencil.jjpod.dpcapp.DELIVERY_INFO_2");
                    i.putExtra("dPhone", dPhone);
                    i.putExtra("dFax", dFax);
                    i.putExtra("dCell", dCell);
                    sendBroadcast(i);

                    Intent j = new Intent(DeliveryScreen2.this, DeliveryScreen3.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(j);
                }
            }
        });

    }
}
