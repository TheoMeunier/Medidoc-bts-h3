package com.example.medidoc_bts_h3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.Activity.Doctor.SearchDoctorActivity;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.home_btn_search_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchDoctorActivity.class));
            }
        });

        return view;
    }

    public void getDoctor(View view) {

        String url = getString(R.string.url_api) + "/doctors";

        Thread t = new Thread(() -> {
            HttpClient httpClient = new HttpClient(getActivity(), url);
            httpClient.setToken(true);
            httpClient.send();

            getActivity().runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    try {
                        JSONObject response = new JSONObject(httpClient.getResponse());

                        JSONArray array = new JSONArray(response.getString("data"));

                        for (int i = 0; i < array.length(); i++){}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //doctorFragment = view.findViewById(R.id.home_list_doctor);
                    //doctorFragment.setAdapter(new DoctorAdapter(Doctors));
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            });

        });

        t.start();
    }
}