package com.detroitpencil.jjpod.dpcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReviewScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_screen);

        getSupportActionBar().setTitle("REVIEW YOUR INFORMATION");
    }
}
