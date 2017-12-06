package com.detroitpencil.jjpod.dpcapp;


/**
 * Created by jjpod on 12/5/2017.
 */

public class Phase2Info {

    public String costPlus, customPrice, pricingNotes;
    public Phase1Info p1info;

    public Phase2Info(){

    }

    public Phase2Info(String costPluss, String customPricee, String pricingNotess){
        //this.p1info = phase1Info;
        this.costPlus = costPluss;
        this.customPrice = customPricee;
        this.pricingNotes = pricingNotess;
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
