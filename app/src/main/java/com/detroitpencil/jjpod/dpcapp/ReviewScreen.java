package com.detroitpencil.jjpod.dpcapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ReviewScreen extends AppCompatActivity {

    String salesName, salesID, company, companyName, address1, address2= "", city, state, zip, phone = "", fax="", cell="",
            apContactName="", apPhone="", apCell="", apOther="", apEmail="", invoices="", poOption, taxable, payment, notes, deliveryCheckOption,
            dAddress1, dAddress2= "", dCity, dState, dZip,dPhone = "", dFax = "", dCell = "", dBuyer ="", dBuyerPhone ="", dBuyerCell ="", dBuyerOther ="", dNotes = "",
            boxOption = "", bsnOption="", wcw ="", iOption="", bsnVersion = "", location="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_screen);
        getSupportActionBar().setTitle("REVIEW YOUR INFORMATION");

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

        RadioButton finalEmailyes = findViewById(R.id.finalEmailYes), finalEmailNo = findViewById(R.id.finalEmailNo),
                finalPOYes = findViewById(R.id.finalPOYes), finalPONo = findViewById(R.id.finalPONo),
                finalTaxYes = findViewById(R.id.finalTaxYes), finalTaxNo = findViewById(R.id.finalTaxNo),
                finalBoxYes = findViewById(R.id.finalBoxYes), finalBoxNo = findViewById(R.id.finalBoxNo),
                finalBSNYes = findViewById(R.id.finalBSNYes), finalBSNNo = findViewById(R.id.finalBSNNo),
                finalMasterUser = findViewById(R.id.finalMasterUser), finalApprovalRouting = findViewById(R.id.finalApprovalRouting);



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
        finalSalespersonText.setEnabled(false);
        finalSalesIDText.setText(salesID);
        finalSalesIDText.setEnabled(false);
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

    }
}
