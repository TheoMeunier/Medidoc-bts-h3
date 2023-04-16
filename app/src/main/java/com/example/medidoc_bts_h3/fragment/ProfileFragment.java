package com.example.medidoc_bts_h3.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.LoginActivity;
import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.ResetPasswordActivity;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Button profileBtnLogout;
    TextView lastName, firstName, email, phone;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        profileBtnLogout = view.findViewById(R.id.profileBtnLogout);
        lastName = view.findViewById(R.id.profileLastName);
        firstName = view.findViewById(R.id.profileFirstName);
        email = view.findViewById(R.id.profileEmail);
        phone = view.findViewById(R.id.profilePhone);

        getUser();

        profileBtnLogout.setOnClickListener(view1 -> {
            logout();
        });

       return view;
    }

    public void logout() {
        String url = getString(R.string.url_api) + "/auth/logout";

        new Thread(() -> {
            HttpClient httpClient = new  HttpClient(getActivity(), url);
            httpClient.setMethod("post");
            httpClient.setToken(true);
            httpClient.send();

            getActivity().runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }

            });
        }).start();
    }

    @SuppressLint("SetTextI18n")
    public void getUser() {
        String url = getString(R.string.url_api) + "/profile";

        new Thread(() -> {
            HttpClient httpClient = new HttpClient(getActivity(), url);
            httpClient.setToken(true);
            httpClient.send();

            getActivity().runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    try {
                        JSONObject response = new JSONObject(httpClient.getResponse());
                        JSONArray array = new JSONArray(response.getString("data"));

                        String response_last_name = array.getJSONObject(0).getString("first_name");
                        String response_first_name = array.getJSONObject(0).getString("last_name");
                        String response_email = array.getJSONObject(0).getString("email");
                        String response_phone = array.getJSONObject(0).getString("phone");

                        lastName.setText("Nom: " + response_last_name);
                        firstName.setText("Prenom: " + response_first_name);
                        email.setText("Email: " + response_email);
                        phone.setText("Phone: " + response_phone);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Error" + code, Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}