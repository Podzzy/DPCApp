package com.detroitpencil.jjpod.dpcapp;

/**
 * Created by jjpod on 12/5/2017.
 */

public class InboxItem {

    private String companyName, date;

    public InboxItem(String cName, String date){
        this.companyName = cName;
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
