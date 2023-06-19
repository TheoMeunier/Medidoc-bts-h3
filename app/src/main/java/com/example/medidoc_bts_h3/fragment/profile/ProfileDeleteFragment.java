package com.example.medidoc_bts_h3.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.Activity.Auth.RegisterActivity;
import com.example.medidoc_bts_h3.serivces.HttpClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileDeleteFragment extends BottomSheetDialogFragment {
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_delete, container, false);

        view.findViewById(R.id.btn_profile_delete).setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCount();
            }
        });

        return view;
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
}