package com.example.medidoc_bts_h3.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView btnLogin;
    EditText inputEmail;
    EditText inputLastName;
    EditText inputFirstName;
    CheckBox inputIsDoctor;
    EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.goToLogin);
        inputLastName = findViewById(R.id.lastName);
        inputFirstName = findViewById(R.id.firstName);
        inputEmail = findViewById(R.id.email);
        inputIsDoctor = findViewById(R.id.isDoctor);
        inputPassword = findViewById(R.id.password);

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
        String email = inputEmail.getText().toString();
        Boolean isDoctor = inputIsDoctor.isChecked();
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
        } else if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password cannot be empty");
            inputPassword.requestFocus();
        } else {
            sendRegister(firstName, lastName, email, isDoctor, password);
        }
    }

    private void sendRegister(String firstName, String lastName, String email, Boolean isDoctor, String password) {
        JSONObject params = new JSONObject();

        try {
            params.put("last_name", lastName);
            params.put("first_name", firstName);
            params.put("email", email);
            params.put("is_doctor", isDoctor);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String data = params.toString();
        String url = getString(R.string.url_api) + "/auth/register";

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient http = new HttpClient(RegisterActivity.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();

                        if (code == 201 || code == 200) {
                            Toast.makeText(RegisterActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else if (code == 422) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");

                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}