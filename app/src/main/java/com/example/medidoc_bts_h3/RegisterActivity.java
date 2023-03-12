package com.example.medidoc_bts_h3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView btnLogin;
    EditText inputEmail;
    EditText inputLastName;
    EditText inputFirstName;
    EditText inputPhone;
    CheckBox inputIsDoctor;
    EditText inputPassword;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.goToLogin);
        inputLastName = findViewById(R.id.lastName);
        inputFirstName = findViewById(R.id.firstName);
        inputPhone = findViewById(R.id.phone);
        inputEmail = findViewById(R.id.email);
        inputIsDoctor = findViewById(R.id.isDoctor);
        inputPassword = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("users");

        btnRegister.setOnClickListener(view -> {
            createUser();
        });

        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser() {
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String phone = inputPhone.getText().toString();
        String email = inputEmail.getText().toString();
        Boolean isDoctor = inputIsDoctor.isSelected();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Email cannot be empty");
            inputEmail.requestFocus();
        } else if (TextUtils.isEmpty(firstName)) {
            inputPassword.setError("First Name cannot be empty");
            inputPassword.requestFocus();
        } else if (TextUtils.isEmpty(lastName)) {
            inputPassword.setError("Last Name cannot be empty");
            inputPassword.requestFocus();
        } else if (TextUtils.isEmpty(phone)) {
            inputPassword.setError("Phone cannot be empty");
            inputPassword.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password cannot be empty");
            inputPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        userInformation(firstName, lastName, phone, isDoctor);

                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    public void userInformation(String firstName, String lastName, String phone, Boolean isDoctor)
    {
        // Récupérer l'utilisateur connecté
        String userId = mAuth.getCurrentUser().getUid();

        User user = new User(firstName, lastName, phone, isDoctor);
        databaseReference.child(userId).setValue(user);
    }
}