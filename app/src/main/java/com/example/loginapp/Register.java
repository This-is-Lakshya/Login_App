package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    // Declaration of Objects
    Button googleBtn;

    Button Register;
    EditText enterEmail, setPass, confirmPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Linking to the xml Objects
        Intent gotoSignUp = new Intent(Register.this, SignUp.class);

        mAuth = FirebaseAuth.getInstance();
        Register = findViewById(R.id.Register);
        enterEmail = findViewById(R.id.enterEmail);
        setPass = findViewById(R.id.setPass);
        confirmPass = findViewById(R.id.confirmPass);

        // ------------

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(enterEmail.getText());
                String password = String.valueOf(setPass.getText());
                String confirmPassword = String.valueOf(confirmPass.getText());

                // -----------
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Set Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Register.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // -----------

                if(password == confirmPassword){
                    Toast.makeText(Register.this, "Password must be same!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Successfully Created!",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Register.this, "Authentication failed!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                // ------------

                startActivity(gotoSignUp);
                finish();

            }
        });

    }
}