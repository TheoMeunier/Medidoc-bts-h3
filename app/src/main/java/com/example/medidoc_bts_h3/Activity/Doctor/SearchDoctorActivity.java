package com.example.medidoc_bts_h3.Activity.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.adapter.DoctorAdapter;
import com.example.medidoc_bts_h3.fragment.HomeFragment;
import com.example.medidoc_bts_h3.models.Doctor;
import com.example.medidoc_bts_h3.serivces.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listView;
    ArrayList<Doctor> doctorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);

        searchView = findViewById(R.id.search_doctor);
        listView = findViewById(R.id.search_list);

        getAllDoctors();

        findViewById(R.id.home_btn_back).setOnClickListener(view -> {
            startActivity(new Intent(SearchDoctorActivity.this, HomeFragment.class));
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public void getAllDoctors() {
        String url = getString(R.string.url_api) + "/doctors";

        Thread t = new Thread(() -> {
            HttpClient httpClient = new HttpClient(this, url);
            httpClient.setToken(true);
            httpClient.send();

            runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    try {
                        JSONObject response = new JSONObject(httpClient.getResponse());
                        JSONArray array = new JSONArray(response.getString("data"));
                        String json = array.toString();

                        Gson gson = new GsonBuilder().create();
                        List<Doctor> doctors = gson.fromJson(json, new TypeToken<List<Doctor>>() {
                        }.getType());

                        doctorsList.addAll(doctors);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RecyclerView searchDoctor = findViewById(R.id.search_list);
                    DoctorAdapter adapter = new DoctorAdapter(this, doctorsList);
                    searchDoctor.setLayoutManager(new LinearLayoutManager(this));
                    searchDoctor.setAdapter(adapter);
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            });

        });

        t.start();
    }
}