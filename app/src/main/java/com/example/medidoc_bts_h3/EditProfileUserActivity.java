package com.example.medidoc_bts_h3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.EditText;
import android.widget.Toast;

import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileUserActivity extends AppCompatActivity {

    Button UpdatbtnRegister;
    EditText inputUpdateEmail;
    EditText inputUpdateLastName;
    EditText inputUpdateFirstName;
    EditText inputUpdatePhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        UpdatbtnRegister=findViewById(R.id.UpdatbtnRegister);
        inputUpdateEmail=findViewById(R.id.Update_email);
        inputUpdateLastName=findViewById(R.id.UpdatelastName);
        inputUpdateFirstName=findViewById(R.id.UpdatefirstName);
        inputUpdatePhone=findViewById(R.id.Updatephone);

        UpdatbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputUpdateEmail.getText().toString();
                String lastName = inputUpdateLastName.getText().toString();
                String firstName = inputUpdateFirstName.getText().toString();
                String phone = inputUpdatePhone.getText().toString();

                UpdateProfile(email, lastName, firstName, phone);
            }
        });


    }

    private void UpdateProfile(String email, String lastName, String firstName, String phone){

        String url = getString(R.string.url_api) + "/profile/edit";

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
            jsonParams.put("last_name", lastName);
            jsonParams.put("first_name", firstName);
            jsonParams.put("phone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            HttpClient httpClient = new HttpClient(EditProfileUserActivity.this, url);
            httpClient.setMethod("post");
            httpClient.setToken(true);
            httpClient.setRequestBody(jsonParams.toString());
            httpClient.send();

            runOnUiThread(() -> {
                int code = httpClient.getStatusCode();

                if (code == 200) {
                    Toast.makeText(EditProfileUserActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditProfileUserActivity.this, "Error: " + code, Toast.LENGTH_SHORT).show();
                    // Handle error case
                }
            });
        }).start();

    }
}