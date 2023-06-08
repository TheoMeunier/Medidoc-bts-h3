package com.example.medidoc_bts_h3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.medidoc_bts_h3.DoctorHomeActivity;
import com.example.medidoc_bts_h3.serivces.HttpClient;
import com.example.medidoc_bts_h3.serivces.LocalStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView btnRegister;
    EditText inputEmail;
    EditText inputPassword;

    LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.goToSignup);

        localStorage = new LocalStorage(LoginActivity.this);

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser() {

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            inputEmail.setError("Email cannot be empty");
            inputEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            inputPassword.setError("Password cannot be empty");
            inputPassword.requestFocus();
        }else{
            JSONObject params = new JSONObject();
            try {
                params.put("email", email);
                params.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String data = params.toString();
            String url = getString(R.string.url_api) + "/auth/login";

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient http = new HttpClient(LoginActivity.this, url);
                    http.setMethod("post");
                    http.setData(data);
                    http.send();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Integer code = http.getStatusCode();
                            if (code == 200) {
                                try {
                                    JSONObject response = new JSONObject(http.getResponse());
                                    String token = response.getString("token");
                                    localStorage.setToken(token);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (code == 422) {
                                try {
                                    JSONObject response = new JSONObject(http.getResponse());
                                    String msg = response.getString("message");
                                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (code == 401) {
                                try {
                                    JSONObject response = new JSONObject(http.getResponse());
                                    String msg = response.getString("message");
                                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Error " + code, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        }
    }
}