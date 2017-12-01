package com.detroitpencil.jjpod.dpcapp;

/**
 * Created by jjpod on 11/26/2017.
 */

public class Phase1Info {
    String salespersonName, salesID, sCompany, companyName, address1, address2, city,
            state, zip, phone, fax, cell, apName, apPhone, apCell, apOther, apEmail,
            apEmailInvoices, poRequired, taxable, pType, notes, dAddress1, dAddress2,
            dCity, dState, dZip, dPhone, dFax, dCell, pBuyer, pBuyerPhone, pBuyerCell, pBuyerOther,
            specialNotes, deliveryLocations, wcw, boxProgram, BSN, version, iSetup;

    public Phase1Info(String salespersonName) {
        this.salespersonName = salespersonName;
    }

    public Phase1Info(String salespersonName, String salesID, String sCompany, String companyName, String address1,
                      String address2, String city, String state, String zip, String phone, String fax, String cell,
                      String apName, String apPhone, String apCell, String apOther, String apEmail, String apEmailInvoices,
                      String poRequired, String taxable, String pType, String notes, String dAddress1, String dAddress2, String dCity,
                      String dState, String dZip, String dPhone, String dFax, String dCell, String pBuyer, String pBuyerPhone, String pBuyerCell,
                      String pBuyerOther, String specialNotes, String deliveryLocations, String wcw, String boxProgram, String BSN, String version, String iSetup) {

        this.salespersonName = salespersonName;
        this.salesID = salesID;
        this.sCompany = sCompany;
        this.companyName = companyName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.fax = fax;
        this.cell = cell;
        this.apName = apName;
        this.apPhone = apPhone;
        this.apCell = apCell;
        this.apOther = apOther;
        this.apEmail = apEmail;
        this.apEmailInvoices = apEmailInvoices;
        this.poRequired = poRequired;
        this.taxable = taxable;
        this.pType = pType;
        this.notes = notes;
        this.dAddress1 = dAddress1;
        this.dAddress2 = dAddress2;
        this.dCity = dCity;
        this.dState = dState;
        this.dZip = dZip;
        this.dPhone = dPhone;
        this.dFax = dFax;
        this.dCell = dCell;
        this.pBuyer = pBuyer;
        this.pBuyerPhone = pBuyerPhone;
        this.pBuyerCell = pBuyerCell;
        this.pBuyerOther = pBuyerOther;
        this.specialNotes = specialNotes;
        this.deliveryLocations = deliveryLocations;
        this.wcw = wcw;
        this.boxProgram = boxProgram;
        this.BSN = BSN;
        this.version = version;
        this.iSetup = iSetup;
    }
}
