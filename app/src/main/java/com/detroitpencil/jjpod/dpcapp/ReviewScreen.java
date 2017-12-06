package com.detroitpencil.jjpod.dpcapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReviewScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String salesName, salesID, company, companyName, address1, address2= "", city, state, zip, phone = "", fax="", cell="",
            apContactName="", apPhone="", apCell="", apOther="", apEmail="", invoices="", poOption, taxable, payment, notes, deliveryCheckOption,
            dAddress1, dAddress2= "", dCity, dState, dZip,dPhone = "", dFax = "", dCell = "", dBuyer ="", dBuyerPhone ="", dBuyerCell ="", dBuyerOther ="", dNotes = "",
            boxOption = "", bsnOption="", wcw ="", iOption="", bsnVersion = "", location="", submitDate;

    String[] companies, paymentOptions;

    Button finalSubmitButton;

    private FirebaseAuth mAuth;
    FirebaseDatabase fd = null;
    DatabaseReference myRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_screen);
        getSupportActionBar().setTitle("REVIEW YOUR INFORMATION");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dformat = new SimpleDateFormat("MM/dd/yyyy");
        submitDate = dformat.format(c.getTime());

        mAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        myRef = fd.getReference();

        finalSubmitButton = findViewById(R.id.finalSubmitButton);
        finalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDB();
                Toast.makeText(ReviewScreen.this, "New account information submitted.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ReviewScreen.this, HomeScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        EditText finalSalespersonText= findViewById(R.id.finalSalespersonText), finalSalesIDText= findViewById(R.id.finalSalesIDText),
                finalCompanyText= findViewById(R.id.finalCompanyText), finalAddress1Text= findViewById(R.id.finalAddress1Text),
                finalAddress2Text= findViewById(R.id.finalAddress2Text), finalCityText= findViewById(R.id.finalCityText),
                finalStateText = findViewById(R.id.finalStateText), finalZipText = findViewById(R.id.finalZipText),
                finalPhoneText = findViewById(R.id.finalPhoneText), //finalFaxText = findViewById(R.id.finalFaxText),
                finalCellText = findViewById(R.id.finalCellText), finalAPContactText = findViewById(R.id.finalAPContactText),
                finalAPPhoneText = findViewById(R.id.finalAPPhoneText), finalAPCellText = findViewById(R.id.finalAPCellText),
                /*finalAPOtherText = findViewById(R.id.finalAPOtherText),*/ finalAPEmailText = findViewById(R.id.finalAPEmailText),
                finalNotesText = findViewById(R.id.finalNotesText), finaldAddress1Text = findViewById(R.id.finalDAddress1Text),
                finalDAddress2Text = findViewById(R.id.finalDAddress2Text), finalDCityText = findViewById(R.id.finalDCityText),
                finalDStateText = findViewById(R.id.finalDStateText), finalDZipText = findViewById(R.id.finalDZipText),
                finalDPhoneText = findViewById(R.id.finalDPhoneText), //finalDFaxText = findViewById(R.id.finalDFaxText),
                finalDCellText = findViewById(R.id.finalDCellText), finalBuyerText = findViewById(R.id.finalBuyerText),
                finalBuyerPhoneText = findViewById(R.id.finalBuyerPhoneText), finalBuyerCellText = findViewById(R.id.finalBuyerCellText),
                /*finalBuyerOtherText = findViewById(R.id.finalBuyerOtherText),*/ finalDNotesText = findViewById(R.id.finalDeliveryNotesText),
                finalWCWText = findViewById(R.id.finalWCWText), finalBSNVersionText = findViewById(R.id.finalVersionText), finalLocationText = findViewById(R.id.finalLocationsText);

        Spinner finalSalesCompanySpinner = findViewById(R.id.finalSalesCompanySpinner) , finalPaymentSpinner = findViewById(R.id.finalPaymentSpinner);

        finalSalesCompanySpinner.setOnItemSelectedListener(this);
        companies = new  String[]{"Detroit Pencil Company", "Supply Geeks", "FRIS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalSalesCompanySpinner.setAdapter(adapter);

        finalPaymentSpinner.setOnItemSelectedListener(this);
        paymentOptions = new  String[]{"Net 30", "Credit Card", "ACH"};
        ArrayAdapter<String> pAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentOptions);
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalPaymentSpinner.setAdapter(pAdapter);

        RadioButton finalEmailyes = findViewById(R.id.finalEmailYes), finalEmailNo = findViewById(R.id.finalEmailNo),
                finalBillingCheckYes = findViewById(R.id.billingCheckYes), finalBillingCheckNo = findViewById(R.id.billingCheckNo),
                finalPOYes = findViewById(R.id.finalPOYes), finalPONo = findViewById(R.id.finalPONo),
                finalTaxYes = findViewById(R.id.finalTaxYes), finalTaxNo = findViewById(R.id.finalTaxNo),
                finalBoxYes = findViewById(R.id.finalBoxYes), finalBoxNo = findViewById(R.id.finalBoxNo),
                finalBSNYes = findViewById(R.id.finalBSNYes), finalBSNNo = findViewById(R.id.finalBSNNo),
                finalMasterUser = findViewById(R.id.finalMasterUser), finalApprovalRouting = findViewById(R.id.finalApprovalRouting);

        TableRow dRow1 = findViewById(R.id.dRow1), dRow2 = findViewById(R.id.dRow2),
                dRow3 = findViewById(R.id.dRow3), dRow4 = findViewById(R.id.dRow4),
                dRow5 = findViewById(R.id.dRow5), dRow6 = findViewById(R.id.dRow6),
                dRow7 = findViewById(R.id.dRow7);

        TextView dRow8 = findViewById(R.id.dRow8);

        //Get all of the data from earlier activities
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        salesName = prefs.getString("salesName", "Error");
        salesID = prefs.getString("salesID", "Error");
        company = prefs.getString("company", "");
        companyName = prefs.getString("companyName", "Error");
        address1 = prefs.getString("address1", "Error");
        address2 = prefs.getString("address2", "");
        city = prefs.getString("city", "Error");
        state = prefs.getString("state", "Error");
        zip = prefs.getString("zip", "Error");
        phone = prefs.getString("phone", "");
        fax = prefs.getString("fax", "");
        cell = prefs.getString("cell","" );
        apContactName = prefs.getString("apContactName","Error");
        apPhone = prefs.getString("apPhone","");
        apCell = prefs.getString("apCell", "");
        apOther = prefs.getString("apOther", "");
        apEmail = prefs.getString("apEmail", "");
        invoices = prefs.getString("invoices", "");
        poOption = prefs.getString("poOption", "");
        taxable = prefs.getString("taxable", "");
        payment = prefs.getString("payment", "");
        notes = prefs.getString("notes", "");
        deliveryCheckOption = prefs.getString("deliveryCheckOption", "");
        dAddress1 = prefs.getString("dAddress1", "Error");
        dAddress2 = prefs.getString("dAddress2", "");
        dCity = prefs.getString("dCity", "Error");
        dState = prefs.getString("dState", "Error");
        dZip = prefs.getString("dZip", "Error");
        dPhone = prefs.getString("dPhone", "");
        dCell = prefs.getString("dCell", "");
        dFax = prefs.getString("dFax", "");
        dBuyer = prefs.getString("dBuyer", "Error");
        dBuyerPhone = prefs.getString("dBuyerPhone", "");
        dBuyerCell = prefs.getString("dBuyerCell", "");
        dBuyerOther = prefs.getString("dBuyerOther", "");
        dNotes = prefs.getString("dNotes", "");
        wcw = prefs.getString("wcw", "");
        location = prefs.getString("location", "");
        boxOption = prefs.getString("boxOption", "");
        bsnOption = prefs.getString("bsnOption","");
        bsnVersion = prefs.getString("bsnVersion", "");
        iOption = prefs.getString("iOption","");

        //Set data review page.
        finalSalespersonText.setText(salesName);
        finalSalesIDText.setText(salesID);
        finalCompanyText.setText(companyName);
        finalAddress1Text.setText(address1);
        finalAddress2Text.setText(address2);
        finalCityText.setText(city);
        finalStateText.setText(state);
        finalZipText.setText(zip);
        finalPhoneText.setText(phone);
        //finalFaxText.setText(fax);
        finalCellText.setText(cell);
        finalAPContactText.setText(apContactName);
        finalAPPhoneText.setText(apPhone);
        finalAPCellText.setText(apCell);
       // finalAPOtherText.setText(apOther);
        finalAPEmailText.setText(apEmail);
        finalNotesText.setText(notes);
        finaldAddress1Text.setText(dAddress1);
        finalDAddress2Text.setText(dAddress2);
        finalDCityText.setText(dCity);
        finalDStateText.setText(dState);
        finalDZipText.setText(dZip);
        finalDPhoneText.setText(dPhone);
        //finalDFaxText.setText(dFax);
        finalDCellText.setText(dCell);
        finalBuyerText.setText(dBuyer);
        finalBuyerPhoneText.setText(dBuyerPhone);
        finalBuyerCellText.setText(dBuyerCell);
       // finalBuyerOtherText.setText(dBuyerOther);
        finalDNotesText.setText(dNotes);
        finalWCWText.setText(wcw);
        finalLocationText.setText(location);
        finalBSNVersionText.setText(bsnVersion);

        //Remove delivery section if delivery info is the same as billing.
        if(deliveryCheckOption.equals("yes")){
            dRow1.setVisibility(View.GONE);
            dRow2.setVisibility(View.GONE);
            dRow3.setVisibility(View.GONE);
            dRow4.setVisibility(View.GONE);
            dRow5.setVisibility(View.GONE);
            dRow6.setVisibility(View.GONE);
            dRow7.setVisibility(View.GONE);
            dRow8.setVisibility(View.GONE);

            dAddress1 = address1;
            dAddress2 = address2;
            dCity = city;
            dState = state;
            dZip = zip;
            dPhone = phone;
            dCell = cell;
            dFax = fax;
            dBuyer = apContactName;
            dBuyerPhone = apPhone;
            dBuyerCell = apCell;
            dBuyerOther = apOther;
            dNotes = notes;

        }

        //Make sure spinners show correct data
        finalSalesCompanySpinner.setSelection(adapter.getPosition(company));
        finalPaymentSpinner.setSelection(pAdapter.getPosition(payment));

        //Set radio buttons
        if(deliveryCheckOption.equals("yes"))
            finalBillingCheckYes.setChecked(true);
        else
            finalBillingCheckNo.setChecked(true);

        if(poOption.equals("yes"))
            finalPOYes.setChecked(true);
        else
            finalPONo.setChecked(true);

        if(taxable.equals("yes"))
            finalTaxYes.setChecked(true);
        else
            finalTaxNo.setChecked(true);

        if(invoices.equals("yes"))
            finalEmailyes.setChecked(true);
        else
            finalEmailNo.setChecked(true);

        if(bsnOption.equals("yes"))
            finalBSNYes.setChecked(true);
        else
            finalBSNNo.setChecked(true);

        if(boxOption.equals("yes"))
            finalBoxYes.setChecked(true);
        else
            finalBoxNo.setChecked(true);

        if(iOption.equals("master user"))
            finalMasterUser.setChecked(true);
        else
            finalApprovalRouting.setChecked(true);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void updateDB(){
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        Phase1Info phase1Info = new Phase1Info(salesName, salesID, company, companyName, address1, address2,
                city, state, zip, phone, fax, cell, apContactName, apPhone, apCell, apOther, apEmail, invoices,
                poOption, taxable, payment, notes, deliveryCheckOption, dAddress1, dAddress2, dCity, dState, dZip, dPhone, dFax, dCell,
                dBuyer, dBuyerPhone, dBuyerCell, dBuyerOther, dNotes, location, wcw, boxOption, bsnOption, bsnVersion, iOption, submitDate,
                "", "", "", "", "", "", "");

        myRef.child("phase2inbox").child(companyName).setValue(phase1Info);


    }
}
