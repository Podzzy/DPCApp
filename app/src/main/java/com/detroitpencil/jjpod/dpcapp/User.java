package com.detroitpencil.jjpod.dpcapp;

/**
 * Created by jjpod on 11/20/2017.
 */

public class User {

    public String email;
    public String password;
    public String phase;

    public User(){

    }

    public User(String email, String password, String phase){
        this.email = email;
        this.password = password;
        this.phase = phase;
    }

    public String getPhase(){
        return phase;
    }

    public void setPhase(String i){
        phase = i;
    }

}
