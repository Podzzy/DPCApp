package com.detroitpencil.jjpod.dpcapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    Button registerButton = null, loginButton = null;
    EditText emailText = null, passwordText = null;
    String email= null, password= null;
    private FirebaseAuth mAuth;
    final String TAG = LoginScreen.class.getSimpleName();
    ProgressDialog progressDialog;
    FirebaseAuth.AuthStateListener mAuthListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();


        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        progressDialog = new ProgressDialog(this);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginUser();
            }
        });


    }

    public void loginUser(){
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(LoginScreen.this,"Email is required.",Toast.LENGTH_SHORT ).show();
            return;
        }

        if( password.equals("")){
            Toast.makeText(LoginScreen.this,"Password is required.",Toast.LENGTH_SHORT ).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( TAG,"signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginScreen.this, "Log in successful.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }

                        // ...
                    }
                });



    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(LoginScreen.this, HomeScreen.class));
        }
    }

    @Override
    public void onStop(){
        super.onStop();

    }
}
