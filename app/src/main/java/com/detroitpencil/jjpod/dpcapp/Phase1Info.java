package com.detroitpencil.jjpod.dpcapp;

/**
 * Created by jjpod on 11/26/2017.
 */

public class Phase1Info {
     private String salespersonName, salesID, sCompany, companyName, address1, address2, city,
            state, zip, phone, fax, cell, apName, apPhone, apCell, apOther, apEmail,
            apEmailInvoices, poRequired, taxable, pType, notes, dAddress1, dAddress2,
            dCity, dState, dZip, dPhone, dFax, dCell, pBuyer, pBuyerPhone, pBuyerCell, pBuyerOther,
            specialNotes, deliveryLocations, wcw, boxProgram, BSN, version, iSetup, deliveryCheckOption, submitDate,
             paperBrand, tonerBrand, matrix, usaExpress, costPlus, customPrice, pricingNotes ;

    public Phase1Info() {

    }

    public Phase1Info(String salespersonName, String salesID, String sCompany, String companyName, String address1,
                      String address2, String city, String state, String zip, String phone, String fax, String cell,
                      String apName, String apPhone, String apCell, String apOther, String apEmail, String apEmailInvoices,
                      String poRequired, String taxable, String pType, String notes, String deliveryCheckOption, String dAddress1, String dAddress2, String dCity,
                      String dState, String dZip, String dPhone, String dFax, String dCell, String pBuyer, String pBuyerPhone, String pBuyerCell,
                      String pBuyerOther, String specialNotes, String deliveryLocations, String wcw, String boxProgram, String BSN, String version, String iSetup, String submitDate,
                      String paperBrand, String tonerBrand, String matrix, String usaExpress, String costPlus, String customPrice, String pricingNotes) {

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
        this.deliveryCheckOption = deliveryCheckOption;
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
        this.submitDate = submitDate;
        this.paperBrand = paperBrand;
        this.tonerBrand = tonerBrand;
        this.matrix = matrix;
        this.usaExpress = usaExpress;
        this.costPlus = costPlus;
        this.customPrice = customPrice;
        this.pricingNotes = pricingNotes;
    }

    public String getSalespersonName() {
        return salespersonName;
    }

    public void setSalespersonName(String salespersonName) {
        this.salespersonName = salespersonName;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getsCompany() {
        return sCompany;
    }

    public void setsCompany(String sCompany) {
        this.sCompany = sCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public String getApPhone() {
        return apPhone;
    }

    public void setApPhone(String apPhone) {
        this.apPhone = apPhone;
    }

    public String getApCell() {
        return apCell;
    }

    public void setApCell(String apCell) {
        this.apCell = apCell;
    }

    public String getApOther() {
        return apOther;
    }

    public void setApOther(String apOther) {
        this.apOther = apOther;
    }

    public String getApEmail() {
        return apEmail;
    }

    public void setApEmail(String apEmail) {
        this.apEmail = apEmail;
    }

    public String getApEmailInvoices() {
        return apEmailInvoices;
    }

    public void setApEmailInvoices(String apEmailInvoices) {
        this.apEmailInvoices = apEmailInvoices;
    }

    public String getPoRequired() {
        return poRequired;
    }

    public void setPoRequired(String poRequired) {
        this.poRequired = poRequired;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getdAddress1() {
        return dAddress1;
    }

    public void setdAddress1(String dAddress1) {
        this.dAddress1 = dAddress1;
    }

    public String getdAddress2() {
        return dAddress2;
    }

    public void setdAddress2(String dAddress2) {
        this.dAddress2 = dAddress2;
    }

    public String getdCity() {
        return dCity;
    }

    public void setdCity(String dCity) {
        this.dCity = dCity;
    }

    public String getdState() {
        return dState;
    }

    public void setdState(String dState) {
        this.dState = dState;
    }

    public String getdZip() {
        return dZip;
    }

    public void setdZip(String dZip) {
        this.dZip = dZip;
    }

    public String getdPhone() {
        return dPhone;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public String getdFax() {
        return dFax;
    }

    public void setdFax(String dFax) {
        this.dFax = dFax;
    }

    public String getdCell() {
        return dCell;
    }

    public void setdCell(String dCell) {
        this.dCell = dCell;
    }

    public String getpBuyer() {
        return pBuyer;
    }

    public void setpBuyer(String pBuyer) {
        this.pBuyer = pBuyer;
    }

    public String getpBuyerPhone() {
        return pBuyerPhone;
    }

    public void setpBuyerPhone(String pBuyerPhone) {
        this.pBuyerPhone = pBuyerPhone;
    }

    public String getpBuyerCell() {
        return pBuyerCell;
    }

    public void setpBuyerCell(String pBuyerCell) {
        this.pBuyerCell = pBuyerCell;
    }

    public String getpBuyerOther() {
        return pBuyerOther;
    }

    public void setpBuyerOther(String pBuyerOther) {
        this.pBuyerOther = pBuyerOther;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(String deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public String getWcw() {
        return wcw;
    }

    public void setWcw(String wcw) {
        this.wcw = wcw;
    }

    public String getBoxProgram() {
        return boxProgram;
    }

    public void setBoxProgram(String boxProgram) {
        this.boxProgram = boxProgram;
    }

    public String getBSN() {
        return BSN;
    }

    public void setBSN(String BSN) {
        this.BSN = BSN;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getiSetup() {
        return iSetup;
    }

    public void setiSetup(String iSetup) {
        this.iSetup = iSetup;
    }

    public void setDeliveryCheckOption(String deliveryCheckOption){
        this.deliveryCheckOption = deliveryCheckOption;
    }

    public String getDeliveryCheckOption(){
        return deliveryCheckOption;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getPaperBrand() {
        return paperBrand;
    }

    public void setPaperBrand(String paperBrand) {
        this.paperBrand = paperBrand;
    }

    public String getTonerBrand() {
        return tonerBrand;
    }

    public void setTonerBrand(String tonerBrand) {
        this.tonerBrand = tonerBrand;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getUsaExpress() {
        return usaExpress;
    }

    public void setUsaExpress(String usaExpress) {
        this.usaExpress = usaExpress;
    }

    public String getCostPlus() {
        return costPlus;
    }

    public void setCostPlus(String costPlus) {
        this.costPlus = costPlus;
    }

    public String getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(String customPrice) {
        this.customPrice = customPrice;
    }

    public String getPricingNotes() {
        return pricingNotes;
    }

    public void setPricingNotes(String pricingNotes) {
        this.pricingNotes = pricingNotes;
    }
}
