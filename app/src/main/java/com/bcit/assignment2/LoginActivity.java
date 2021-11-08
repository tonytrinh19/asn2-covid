package com.bcit.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    ProgressBar progressBar;
    Button btnLogin;
    TextView tvCreateNewAccount;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail    = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        progressBar   = findViewById(R.id.progressBar);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateNewAccount = findViewById(R.id.tvCreateNewAccount);

        fAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                editEmail.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                editEmail.setError("Password is required");
                return;
            }

            if (password.length() < 6) {
                editEmail.setError("Password must be >= 6 characters.");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        });

        tvCreateNewAccount.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
    }

}