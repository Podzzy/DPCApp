package com.detroitpencil.jjpod.dpcapp;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReviewP2InfoScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String salesName, salesID, company, companyName, address1, address2= "", city, state, zip, phone = "", fax="", cell="",
            apContactName="", apPhone="", apCell="", apOther="", apEmail="", invoices="", poOption, taxable, payment, notes, deliveryCheckOption,
            dAddress1, dAddress2= "", dCity, dState, dZip,dPhone = "", dFax = "", dCell = "", dBuyer ="", dBuyerPhone ="", dBuyerCell ="", dBuyerOther ="", dNotes = "",
            boxOption = "", bsnOption="", wcw ="", iOption="", bsnVersion = "", location="", paperBrand, tonerBrand, matrix, usaExpress,costPlus,customPrice,pNotes, status="";

    EditText finalSalespersonText, finalSalesIDText, finalCompanyText, finalAddress1Text,
            finalAddress2Text, finalCityText, finalStateText , finalZipText ,
            finalPhoneText, //finalFaxText = findViewById(R.id.finalFaxText),
            finalCellText , finalAPContactText ,
            finalAPPhoneText , finalAPCellText , /*finalAPOtherText = findViewById(R.id.finalAPOtherText),*/ finalAPEmailText,
            finalNotesText , finaldAddress1Text , finalDAddress2Text , finalDCityText ,
            finalDStateText , finalDZipText , finalDPhoneText , //finalDFaxText = findViewById(R.id.finalDFaxText),
            finalDCellText , finalBuyerText, finalBuyerPhoneText , finalBuyerCellText ,
    /*finalBuyerOtherText = findViewById(R.id.finalBuyerOtherText),*/ finalDNotesText,
            finalWCWText , finalBSNVersionText, finalLocationText, finalCostPlusText, finalCustomPriceText, finalPNotesText;

    Spinner finalSalesCompanySpinner , finalPaymentSpinner,  finalPaperSpinner, finalTonerSpinner, finalMatrixSpinner;

    RadioButton finalEmailyes , finalEmailNo , finalBillingCheckYes , finalBillingCheckNo ,
            finalPOYes , finalPONo , finalTaxYes , finalTaxNo ,
            finalBoxYes , finalBoxNo, finalBSNYes, finalBSNNo ,
            finalMasterUser, finalApprovalRouting, USAExpressYes, USAExpressNo;

    TableRow dRow1 , dRow2,
            dRow3 , dRow4 ,
            dRow5 , dRow6,
            dRow7;

    TextView dRow8 ;

    String[] companies, paymentOptions, paperBrands, tonerBrands, matrixItems;

    ArrayAdapter<String> adapter, pAdapter, paperAdapter, tonerAdapter, matrixAdapter;

    final String TAG = Phase2InboxScreen.class.getSimpleName();

    Button nextScreenButton3;

    private FirebaseAuth mAuth;
    FirebaseDatabase fd = null;
    DatabaseReference myRef = null;
    DatabaseReference fromRef, toRef ;

    String inboxCompany;

    Phase1Info phase1Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_p2_info_screen);

        getSupportActionBar().setTitle("REVIEW PHASE 2 INFO");

        mAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        myRef = fd.getReference();

        Intent j = getIntent();
        inboxCompany = j.getStringExtra("inboxCompany");
        status = j.getStringExtra("status");

        nextScreenButton3 = findViewById(R.id.nextScreeButton3);

        if(status!=null){
            if(status.equals("yes")){
                nextScreenButton3.setVisibility(View.GONE);
                getSupportActionBar().setTitle(inboxCompany);
            }
        }

        nextScreenButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ReviewP2InfoScreen.this, HomeScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });


        finalSalespersonText= findViewById(R.id.finalSalespersonText3);
        finalSalesIDText= findViewById(R.id.finalSalesIDText3);
        finalCompanyText= findViewById(R.id.finalCompanyText3); finalAddress1Text= findViewById(R.id.finalAddress1Text3);
        finalAddress2Text= findViewById(R.id.finalAddress2Text3); finalCityText= findViewById(R.id.finalCityText3);
        finalStateText = findViewById(R.id.finalStateText3); finalZipText = findViewById(R.id.finalZipText3);
        finalPhoneText = findViewById(R.id.finalPhoneText3); //finalFaxText = findViewById(R.id.finalFaxText),
        finalCellText = findViewById(R.id.finalCellText3); finalAPContactText = findViewById(R.id.finalAPContactText3);
        finalAPPhoneText = findViewById(R.id.finalAPPhoneText3); finalAPCellText = findViewById(R.id.finalAPCellText3);
                /*finalAPOtherText = findViewById(R.id.finalAPOtherText),*/ finalAPEmailText = findViewById(R.id.finalAPEmailText3);
        finalNotesText = findViewById(R.id.finalNotesText3); finaldAddress1Text = findViewById(R.id.finalDAddress1Text3);
        finalDAddress2Text = findViewById(R.id.finalDAddress2Text3); finalDCityText = findViewById(R.id.finalDCityText3);
        finalDStateText = findViewById(R.id.finalDStateText3); finalDZipText = findViewById(R.id.finalDZipText3);
        finalDPhoneText = findViewById(R.id.finalDPhoneText3); //finalDFaxText = findViewById(R.id.finalDFaxText),
        finalDCellText = findViewById(R.id.finalDCellText3); finalBuyerText = findViewById(R.id.finalBuyerText3);
        finalBuyerPhoneText = findViewById(R.id.finalBuyerPhoneText3); finalBuyerCellText = findViewById(R.id.finalBuyerCellText3);
                /*finalBuyerOtherText = findViewById(R.id.finalBuyerOtherText),*/ finalDNotesText = findViewById(R.id.finalDeliveryNotesText3);
        finalWCWText = findViewById(R.id.finalWCWText3); finalBSNVersionText = findViewById(R.id.finalVersionText3);finalLocationText = findViewById(R.id.finalLocationsText3);
        finalCostPlusText = findViewById(R.id.finalCostPlusText3); finalCustomPriceText = findViewById(R.id.finalCustomPriceText3); finalPNotesText = findViewById(R.id.finalPricingNotesText3);

        finalSalesCompanySpinner = findViewById(R.id.finalSalesCompanySpinner3);
        finalPaymentSpinner = findViewById(R.id.finalPaymentSpinner3);
        finalPaperSpinner = findViewById(R.id.finalPaperSpinner3);
        finalTonerSpinner = findViewById(R.id.finalTonerSpinner3);
        finalMatrixSpinner = findViewById(R.id.finalMatrixSpinner3);

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

        finalPaperSpinner.setOnItemSelectedListener(this);
        paperBrands = new  String[]{"P8", "P10", "P14", "P17", "P20"};
        paperAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paperBrands);
        paperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalPaperSpinner.setAdapter(paperAdapter);

        finalTonerSpinner.setOnItemSelectedListener(this);
        tonerBrands = new  String[]{"20A-5", "20A-10", "20A+5", "20A7GP"};
        tonerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tonerBrands);
        paperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalTonerSpinner.setAdapter(tonerAdapter);

        finalMatrixSpinner.setOnItemSelectedListener(this);
        matrixItems = new  String[]{"2015", "2016", "20A", "20C", "50A", "38S"};
        matrixAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, matrixItems);
        matrixAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finalMatrixSpinner.setAdapter(matrixAdapter);


        finalEmailyes = findViewById(R.id.finalEmailYes3); finalEmailNo = findViewById(R.id.finalEmailNo3);
        finalBillingCheckYes = findViewById(R.id.billingCheckYes3); finalBillingCheckNo = findViewById(R.id.billingCheckNo3);
        finalPOYes = findViewById(R.id.finalPOYes3); finalPONo = findViewById(R.id.finalPONo3);
        finalTaxYes = findViewById(R.id.finalTaxYes3); finalTaxNo = findViewById(R.id.finalTaxNo3);
        finalBoxYes = findViewById(R.id.finalBoxYes3); finalBoxNo = findViewById(R.id.finalBoxNo3);
        finalBSNYes = findViewById(R.id.finalBSNYes3); finalBSNNo = findViewById(R.id.finalBSNNo3);
        finalMasterUser = findViewById(R.id.finalMasterUser3); finalApprovalRouting = findViewById(R.id.finalApprovalRouting3);
        USAExpressYes = findViewById(R.id.finalUSAExpressYes3); USAExpressNo = findViewById(R.id.finalUSAExpressNo3);

        dRow1 = findViewById(R.id.dRow13); dRow2 = findViewById(R.id.dRow23);
        dRow3 = findViewById(R.id.dRow33); dRow4 = findViewById(R.id.dRow43);
        dRow5 = findViewById(R.id.dRow53); dRow6 = findViewById(R.id.dRow63);
        dRow7 = findViewById(R.id.dRow73);

        dRow8 = findViewById(R.id.dRow83);



        myRef.child("phase3inbox").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phase1Info = dataSnapshot.child(inboxCompany).getValue(Phase1Info.class);
                if(phase1Info== null){
                    //Toast.makeText(ReviewP1InfoScreen.this, "Phase1Info is null.", Toast.LENGTH_SHORT).show();
                    return;
                }
                decompInfo2(phase1Info);
                updateUI2();
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

    public void decompInfo2(Phase1Info phase1Info){
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
        paperBrand = phase1Info.getPaperBrand();
        tonerBrand = phase1Info.getTonerBrand();
        matrix = phase1Info.getMatrix();
        usaExpress = phase1Info.getUsaExpress();
        costPlus = phase1Info.getCostPlus();
        customPrice = phase1Info.getCustomPrice();
        pNotes = phase1Info.getPricingNotes();
    }

    public void updateUI2(){
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
        finalCostPlusText.setText(costPlus);
        finalCustomPriceText.setText(customPrice);
        finalPNotesText.setText(pNotes);

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
        finalPaperSpinner.setSelection(paperAdapter.getPosition(paperBrand));
        finalTonerSpinner.setSelection(tonerAdapter.getPosition(tonerBrand));
        finalMatrixSpinner.setSelection(matrixAdapter.getPosition(matrix));

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

        if(usaExpress.equals("yes"))
            USAExpressYes.setChecked(true);
        else
            USAExpressNo.setChecked(true);

        disableViews3();
    }

    public void disableViews3(){
        finalSalespersonText.setEnabled(false);
        finalSalesIDText.setEnabled(false);
        finalCompanyText.setEnabled(false);
        finalAddress1Text.setEnabled(false);
        finalAddress2Text.setEnabled(false);
        finalCityText.setEnabled(false);
        finalStateText.setEnabled(false);
        finalZipText.setEnabled(false);
        finalPhoneText.setEnabled(false);
        //finalFaxText.setText(fax);
        finalCellText.setEnabled(false);
        finalAPContactText.setEnabled(false);
        finalAPPhoneText.setEnabled(false);
        finalAPCellText.setEnabled(false);
        // finalAPOtherText.setText(apOther);
        finalAPEmailText.setEnabled(false);
        finalNotesText.setEnabled(false);
        finaldAddress1Text.setEnabled(false);
        finalDAddress2Text.setEnabled(false);
        finalDCityText.setEnabled(false);
        finalDStateText.setEnabled(false);
        finalDZipText.setEnabled(false);
        finalDPhoneText.setEnabled(false);
        //finalDFaxText.setText(dFax);
        finalDCellText.setEnabled(false);
        finalBuyerText.setEnabled(false);
        finalBuyerPhoneText.setEnabled(false);
        finalBuyerCellText.setEnabled(false);
        // finalBuyerOtherText.setText(dBuyerOther);
        finalDNotesText.setEnabled(false);
        finalWCWText.setEnabled(false);
        finalLocationText.setEnabled(false);
        finalBSNVersionText.setEnabled(false);
        finalCostPlusText.setEnabled(false);
        finalCustomPriceText.setEnabled(false);
        finalPNotesText.setEnabled(false);



        finalSalesCompanySpinner.setEnabled(false);
        finalPaymentSpinner.setEnabled(false);
        finalSalesCompanySpinner.setEnabled(false);
        finalPaymentSpinner.setEnabled(false);
        finalPaperSpinner.setEnabled(false);
        finalTonerSpinner.setEnabled(false);
        finalMatrixSpinner.setEnabled(false);;

        /*
        finalEmailyes.setEnabled(false);
        finalEmailNo.setEnabled(false);
        finalBillingCheckYes.setEnabled(false);
        finalBillingCheckNo.setEnabled(false);
        finalPOYes.setEnabled(false);
        finalPONo.setEnabled(false);
        finalTaxYes.setEnabled(false);
        finalTaxNo.setEnabled(false);
        finalBoxYes.setEnabled(false);
        finalBoxNo.setEnabled(false);
        finalBSNYes.setEnabled(false);
        finalBSNNo.setEnabled(false);
        finalMasterUser.setEnabled(false);
        finalApprovalRouting.setEnabled(false);
        finalEmailyes.setEnabled(false);
        finalEmailNo.setEnabled(false);
        finalBillingCheckYes.setEnabled(false);
        finalBillingCheckNo.setEnabled(false);
        finalPOYes.setEnabled(false);
        finalPONo.setEnabled(false);
        finalTaxYes.setEnabled(false);
        finalTaxNo.setEnabled(false);
        finalBoxYes.setEnabled(false);
        finalBoxNo.setEnabled(false);
        finalBSNYes.setEnabled(false);
        finalBSNNo.setEnabled(false);
        finalMasterUser.setEnabled(false);
        finalApprovalRouting.setEnabled(false);
        USAExpressYes.setEnabled(false);
        USAExpressNo.setEnabled(false);
        */
    }



}
