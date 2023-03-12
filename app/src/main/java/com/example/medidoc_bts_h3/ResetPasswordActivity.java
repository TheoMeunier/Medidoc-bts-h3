package com.example.medidoc_bts_h3;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ResetPasswordActivity extends AppCompatActivity {

    Button btnResetPassword;
    TextView btnGoToLogin;
    EditText inputEmail;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnGoToLogin = findViewById(R.id.goToLogin);
        inputEmail = findViewById(R.id.email);

        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(view -> {
            resetPassword();
        });

        btnGoToLogin.setOnClickListener(view -> {
            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
        });
    }

    private void resetPassword() {
        String emailAddress = inputEmail.getText().toString();

        mAuth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ResetPasswordActivity.this, "Send mail successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Send mail Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}