package com.example.medidoc_bts_h3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.adapter.DoctorAdapter;
import com.example.medidoc_bts_h3.fragment.HomeFragment;
import com.example.medidoc_bts_h3.models.Doctor;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchDoctorActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listView;
    //ArrayList<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);

        searchView = findViewById(R.id.search_doctor);
        listView = findViewById(R.id.search_list);

        DoctorAdapter adapter = new DoctorAdapter();
        listView.setAdapter(adapter);

        findViewById(R.id.home_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchDoctorActivity.this, HomeFragment.class));
            }
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

    public void getDoctor(View view) {
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

                        for (int i = 0; i < array.length(); i++){}
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