package com.example.medidoc_bts_h3.Activity.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.adapter.DoctorAdapter;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorShowActivity extends AppCompatActivity {

    TextView name, speciality, address, postalCodeCity, email, phone, description;

    String doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show);

        name = findViewById(R.id.show_doctor_name);
        speciality = findViewById(R.id.show_doctor_spce);
        address = findViewById(R.id.show_doctor_address);
        postalCodeCity = findViewById(R.id.show_doctor_postal_code_city);
        description = findViewById(R.id.show_doctor_description);
        email = findViewById(R.id.show_doctor_email);
        phone = findViewById(R.id.show_doctor_phone);

        String id = getIntent().getStringExtra("id");
        doctorId = id;

        getFindDoctor();

        findViewById(R.id.show_doctor_icon_back).setOnClickListener(view -> {
            Intent intent = new Intent(DoctorShowActivity.this, SearchDoctorActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    public void getFindDoctor() {
        String url = getString(R.string.url_api) + "/doctors/" + doctorId;

        Thread t = new Thread(() -> {
            HttpClient httpClient = new HttpClient(this, url);
            httpClient.setToken(true);
            httpClient.send();

            runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    try {

                        JSONObject response = new JSONObject(httpClient.getResponse());
                        JSONObject data = response.getJSONObject("data");

                        String responseFirstName = data.getString("first_name");
                        String responseLastName = data.getString("last_name");
                        String responseSpeciality = data.getString("speciality");
                        String responseAddress = data.getString("address");
                        String responsePostalCode = data.getString("postal_code");
                        String responseCity = data.getString("city");
                        String responseDescription = data.getString("description");
                        String responseEmail = data.getString("email");
                        String responsePhone = data.getString("phone");

                        name.setText(responseLastName + " " + responseFirstName);
                        speciality.setText(responseSpeciality);
                        address.setText(responseAddress);
                        postalCodeCity.setText(responsePostalCode + " " + responseCity);
                        description.setText(responseDescription);
                        phone.setText(responsePhone);
                        email.setText(responseEmail);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        });

        t.start();
    }
}