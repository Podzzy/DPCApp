package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReviewP1InfoScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String salesName, salesID, company, companyName, address1, address2= "", city, state, zip, phone = "", fax="", cell="",
            apContactName="", apPhone="", apCell="", apOther="", apEmail="", invoices="", poOption, taxable, payment, notes, deliveryCheckOption,
            dAddress1, dAddress2= "", dCity, dState, dZip,dPhone = "", dFax = "", dCell = "", dBuyer ="", dBuyerPhone ="", dBuyerCell ="", dBuyerOther ="", dNotes = "",
            boxOption = "", bsnOption="", wcw ="", iOption="", bsnVersion = "", location="";

    EditText finalSalespersonText, finalSalesIDText, finalCompanyText, finalAddress1Text,
            finalAddress2Text, finalCityText, finalStateText , finalZipText ,
            finalPhoneText, //finalFaxText = findViewById(R.id.finalFaxText),
             finalCellText , finalAPContactText ,
            finalAPPhoneText , finalAPCellText , /*finalAPOtherText = findViewById(R.id.finalAPOtherText),*/ finalAPEmailText,
            finalNotesText , finaldAddress1Text , finalDAddress2Text , finalDCityText ,
            finalDStateText , finalDZipText , finalDPhoneText , //finalDFaxText = findViewById(R.id.finalDFaxText),
            finalDCellText , finalBuyerText, finalBuyerPhoneText , finalBuyerCellText ,
            /*finalBuyerOtherText = findViewById(R.id.finalBuyerOtherText),*/ finalDNotesText,
            finalWCWText , finalBSNVersionText, finalLocationText;

    Spinner finalSalesCompanySpinner , finalPaymentSpinner ;

    RadioButton finalEmailyes , finalEmailNo , finalBillingCheckYes , finalBillingCheckNo ,
            finalPOYes , finalPONo , finalTaxYes , finalTaxNo ,
            finalBoxYes , finalBoxNo, finalBSNYes, finalBSNNo ,
            finalMasterUser, finalApprovalRouting;

    TableRow dRow1 , dRow2,
            dRow3 , dRow4 ,
            dRow5 , dRow6,
            dRow7;

    TextView dRow8 ;

    String[] companies, paymentOptions;

    ArrayAdapter<String> adapter, pAdapter;

    final String TAG = Phase2InboxScreen.class.getSimpleName();

    Button  nextScreenButton;

    private FirebaseAuth mAuth;
    FirebaseDatabase fd = null;
    DatabaseReference myRef = null;
    DatabaseReference fromRef, toRef ;

    String inboxCompany;

    Phase1Info phase1Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_p1_info_screen);

        getSupportActionBar().setTitle("REVIEW PHASE 1 INFO");

        mAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        myRef = fd.getReference();

        Intent j = getIntent();
        inboxCompany = j.getStringExtra("inboxCompany");


        nextScreenButton = findViewById(R.id.nextScreeButton);
        nextScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ReviewP1InfoScreen.this, PricingProfilesScreen1.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                i.putExtra("inboxCompany", inboxCompany);
                startActivity(i);

            }
        });


         finalSalespersonText= findViewById(R.id.finalSalespersonText2);
         finalSalesIDText= findViewById(R.id.finalSalesIDText2);
                finalCompanyText= findViewById(R.id.finalCompanyText2); finalAddress1Text= findViewById(R.id.finalAddress1Text2);
                finalAddress2Text= findViewById(R.id.finalAddress2Text2); finalCityText= findViewById(R.id.finalCityText2);
                finalStateText = findViewById(R.id.finalStateText2); finalZipText = findViewById(R.id.finalZipText2);
                finalPhoneText = findViewById(R.id.finalPhoneText2); //finalFaxText = findViewById(R.id.finalFaxText),
                finalCellText = findViewById(R.id.finalCellText2); finalAPContactText = findViewById(R.id.finalAPContactText2);
                finalAPPhoneText = findViewById(R.id.finalAPPhoneText2); finalAPCellText = findViewById(R.id.finalAPCellText2);
                /*finalAPOtherText = findViewById(R.id.finalAPOtherText),*/ finalAPEmailText = findViewById(R.id.finalAPEmailText2);
                finalNotesText = findViewById(R.id.finalNotesText2); finaldAddress1Text = findViewById(R.id.finalDAddress1Text2);
                finalDAddress2Text = findViewById(R.id.finalDAddress2Text2); finalDCityText = findViewById(R.id.finalDCityText2);
                finalDStateText = findViewById(R.id.finalDStateText2); finalDZipText = findViewById(R.id.finalDZipText2);
                finalDPhoneText = findViewById(R.id.finalDPhoneText2); //finalDFaxText = findViewById(R.id.finalDFaxText),
                finalDCellText = findViewById(R.id.finalDCellText2); finalBuyerText = findViewById(R.id.finalBuyerText2);
                finalBuyerPhoneText = findViewById(R.id.finalBuyerPhoneText2); finalBuyerCellText = findViewById(R.id.finalBuyerCellText2);
                /*finalBuyerOtherText = findViewById(R.id.finalBuyerOtherText),*/ finalDNotesText = findViewById(R.id.finalDeliveryNotesText2);
                finalWCWText = findViewById(R.id.finalWCWText2); finalBSNVersionText = findViewById(R.id.finalVersionText2);finalLocationText = findViewById(R.id.finalLocationsText2);

        finalSalesCompanySpinner = findViewById(R.id.finalSalesCompanySpinner2);
        finalPaymentSpinner = findViewById(R.id.finalPaymentSpinner2);

        finalSalesCompanySpinner.setOnItemSelectedListener(this);
        companies = new  String[]{"Detroit Pencil Company", "Supply Geeks", "FRIS"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalSalesCompanySpinner.setAdapter(adapter);

        finalPaymentSpinner.setOnItemSelectedListener(this);
        paymentOptions = new  String[]{"Net 30", "Credit Card", "ACH"};
        pAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentOptions);
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalPaymentSpinner.setAdapter(pAdapter);

        finalEmailyes = findViewById(R.id.finalEmailYes2); finalEmailNo = findViewById(R.id.finalEmailNo2);
        finalBillingCheckYes = findViewById(R.id.billingCheckYes2); finalBillingCheckNo = findViewById(R.id.billingCheckNo2);
        finalPOYes = findViewById(R.id.finalPOYes2); finalPONo = findViewById(R.id.finalPONo2);
        finalTaxYes = findViewById(R.id.finalTaxYes2); finalTaxNo = findViewById(R.id.finalTaxNo2);
        finalBoxYes = findViewById(R.id.finalBoxYes2); finalBoxNo = findViewById(R.id.finalBoxNo2);
        finalBSNYes = findViewById(R.id.finalBSNYes2); finalBSNNo = findViewById(R.id.finalBSNNo2);
        finalMasterUser = findViewById(R.id.finalMasterUser2); finalApprovalRouting = findViewById(R.id.finalApprovalRouting2);

        dRow1 = findViewById(R.id.dRow12); dRow2 = findViewById(R.id.dRow22);
        dRow3 = findViewById(R.id.dRow32); dRow4 = findViewById(R.id.dRow42);
        dRow5 = findViewById(R.id.dRow52); dRow6 = findViewById(R.id.dRow62);
        dRow7 = findViewById(R.id.dRow72);

        dRow8 = findViewById(R.id.dRow82);



        myRef.child("phase2inbox").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phase1Info = dataSnapshot.child(inboxCompany).getValue(Phase1Info.class);
                if(phase1Info== null){
                    //Toast.makeText(ReviewP1InfoScreen.this, "Phase1Info is null.", Toast.LENGTH_SHORT).show();
                    return;
                }
                decompInfo(phase1Info);
                updateUI();
                Log.w(TAG,phase1Info.getSalespersonName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void decompInfo(Phase1Info phase1Info){
        if(phase1Info == null){
            //Toast.makeText(this, "Phase1Info is null", Toast.LENGTH_SHORT).show();
            return;
        }


        salesName = phase1Info.getSalespersonName();
        salesID = phase1Info.getSalesID();
        company = phase1Info.getsCompany();
        companyName = phase1Info.getCompanyName();
        address1 = phase1Info.getAddress1();
        address2 = phase1Info.getAddress2();
        city = phase1Info.getCity();
        state = phase1Info.getState();
        zip = phase1Info.getZip();
        phone = phase1Info.getPhone();
        fax = phase1Info.getFax();
        cell = phase1Info.getCell();
        apContactName = phase1Info.getApName();
        apPhone = phase1Info.getApPhone();
        apCell = phase1Info.getApCell();
        apOther = phase1Info.getApOther();
        apEmail = phase1Info.getApEmail();
        invoices = phase1Info.getApEmailInvoices();
        poOption = phase1Info.getPoRequired();
        taxable = phase1Info.getTaxable();
        payment = phase1Info.getpType();
        notes = phase1Info.getNotes();
        deliveryCheckOption = phase1Info.getDeliveryCheckOption();
        dAddress1 = phase1Info.getdAddress1();
        dAddress2 = phase1Info.getdAddress2();
        dCity = phase1Info.getdCity();
        dState = phase1Info.getdState();
        dZip = phase1Info.getdZip();
        dPhone = phase1Info.getdPhone();
        dFax = phase1Info.getdFax();
        dCell = phase1Info.getdCell();
        dBuyer = phase1Info.getpBuyer();
        dBuyerPhone = phase1Info.getpBuyerPhone();
        dBuyerCell = phase1Info.getpBuyerCell();
        dBuyerOther = phase1Info.getpBuyerOther();
        dNotes = phase1Info.getNotes();
        boxOption = phase1Info.getBoxProgram();
        bsnOption = phase1Info.getBSN();
        wcw = phase1Info.getWcw();
        iOption = phase1Info.getiSetup();
        bsnVersion = phase1Info.getVersion();
        location = phase1Info.getDeliveryLocations();
    }

    public void updateUI(){
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




}
