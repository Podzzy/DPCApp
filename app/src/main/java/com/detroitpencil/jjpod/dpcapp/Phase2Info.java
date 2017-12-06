package com.detroitpencil.jjpod.dpcapp;


/**
 * Created by jjpod on 12/5/2017.
 */

public class Phase2Info {

    public String costPlus, customPrice, pricingNotes;
    public Phase1Info phase1Info;

    public Phase2Info(){

    }

    public Phase2Info(Phase1Info phase1Info, String costPluss, String customPricee, String pricingNotess){
        this.phase1Info = phase1Info;
        this.costPlus = costPluss;
        this.customPrice = customPricee;
        this.pricingNotes = pricingNotess;
    }

    public String getSubmit2Date(){
        return phase1Info.getSubmitDate();
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

    public Phase1Info getP1info() {
        return phase1Info;
    }

    public void setPhase1Info(Phase1Info p1info) {
        this.phase1Info = p1info;
    }
}
