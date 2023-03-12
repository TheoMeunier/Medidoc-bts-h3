package com.example.medidoc_bts_h3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        auth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(view ->{
            auth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}