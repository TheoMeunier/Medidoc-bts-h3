package com.example.medidoc_bts_h3.fragment.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medidoc_bts_h3.LoginActivity;
import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.RegisterActivity;
import com.example.medidoc_bts_h3.serivces.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    TextView profile, email, phone, password;
    CardView logout;


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
        profile = view.findViewById(R.id.profile);
        email = view.findViewById(R.id.profile_email);
        phone = view.findViewById(R.id.profile_phone);
        password = view.findViewById(R.id.profile_password);
        logout = view.findViewById(R.id.profile_logout);

        CardView logout = view.findViewById(R.id.profile_logout);
        CardView deleteCount = view.findViewById(R.id.profile_delete_count);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        deleteCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCount();
            }
        });

        getUser();

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

    public void deleteCount() {
        String url = getString(R.string.url_api) + "/profile/delete";

        new Thread(() -> {
            HttpClient httpClient = new  HttpClient(getActivity(), url);
            httpClient.setMethod("post");
            httpClient.setToken(true);
            httpClient.send();

            getActivity().runOnUiThread(() -> {
                Integer code = httpClient.getStatusCode();

                if (code == 200) {
                    startActivity(new Intent(getActivity(), RegisterActivity.class));
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

                        profile.setText(response_first_name + " " + response_last_name);
                        email.setText(response_email);
                        phone.setText(response_phone);
                        password.setText("********");

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
