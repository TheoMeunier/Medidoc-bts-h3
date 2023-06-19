package com.example.medidoc_bts_h3.fragment.appointement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.adapter.appointment.AppointementFuturAdapter;
import com.example.medidoc_bts_h3.models.AppointementFuture;
import com.example.medidoc_bts_h3.serivces.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppointementFutureFragment extends Fragment {

    ArrayList<AppointementFuture> AppointementFuture = new ArrayList<>();
    //RecyclerView appointmentFutureFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointement_future, container, false);

        //getAllAssignmentFuture(view);

        RecyclerView appointmentFutureFragment = view.findViewById(R.id.recyclerViewRdvFuture);
        appointmentFutureFragment.setAdapter(new AppointementFuturAdapter());

        return view;
    }

    public void getAllAssignmentFuture(View view) {
        String url = getString(R.string.url_api) + "/appoints/future";

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
                        String json = array.toString();

                        Gson gson = new GsonBuilder().create();
                        List<AppointementFuture> futureList = gson.fromJson(json, new TypeToken<List<AppointementFuture>>(){}.getType());

                        AppointementFuture.addAll(futureList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //appointmentFutureFragment = view.findViewById(R.id.recyclerViewRdv);
                    //appointmentFutureFragment.setAdapter(new AppointementFuturAdapter(AppointementFuture));
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            });

        });

        t.start();
    }
}