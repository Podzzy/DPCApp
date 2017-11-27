package com.detroitpencil.jjpod.dpcapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner = null;
    String[] phases = null;
    private FirebaseAuth mAuth;
    String currentPhase = null;
    EditText editTextEmail= null, editTextPassword = null, editTextPasswordCheck = null;
    Button regButton = null;
    String email = null, password = null, passwordCheck = null;
    ProgressDialog progressDialog = null;
    FirebaseDatabase fd = null;
    DatabaseReference myRef = null;
    FirebaseAuth.AuthStateListener mAuthListener = null;
    TextView signInText = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        getSupportActionBar().setTitle("REGISTER");

        mAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        myRef = fd.getReference();



        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        phases = new String[]{"Phase 1", "Phase 2", "Phase 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, phases);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordCheck = findViewById(R.id.editTextPasswordCheck);

        signInText = findViewById(R.id.signInText);

        progressDialog = new ProgressDialog(this);

        regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),LoginScreen.class));
                finish();
            }
        });

    }

    private void registerUser(){
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        passwordCheck = editTextPasswordCheck.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordCheck) || !passwordCheck.equals(password)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid();
                            User newUser = new User(email, password, currentPhase);
                            myRef.child("users").child(userID).setValue(newUser);

                            Toast.makeText(RegisterScreen.this, "Registered!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                        }else{
                            Toast.makeText(RegisterScreen.this, "Failed to create user:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                currentPhase = "Phase 1";
                break;
            case 1:
                currentPhase = "Phase 2";
                break;
            case 2:
                currentPhase = "Phase 3";
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(RegisterScreen.this, "Select a phase!", Toast.LENGTH_SHORT).show();
    }
}
