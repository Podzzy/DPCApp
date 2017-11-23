package com.detroitpencil.jjpod.dpcapp;

/**
 * Created by jjpod on 11/20/2017.
 */

public class User {

    public String email;
    public String password;
    public int phase;

    public User(){

    }

    public User(String email, String password, int phase){
        this.email = email;
        this.password = password;
        this.phase = phase;
    }

    public int getPhase(){
        return phase;
    }

    public void setPhase(int i){
        phase = i;
    }

}
