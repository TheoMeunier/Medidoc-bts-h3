package com.example.medidoc_bts_h3.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.serivces.HttpClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileEditEmailFragment extends BottomSheetDialogFragment {

    EditText inputEmail;
    private final String email;

    public ProfileEditEmailFragment(String email) {
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit_email, container, false);

        inputEmail = view.findViewById(R.id.input_porfile_email);
        inputEmail.setText(this.email);

        view.findViewById(R.id.btn_profile_edit_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmail();
            }
        });

        return view;
    }

    public void editEmail() {
        String email = inputEmail.getText().toString();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Email cannot be empty");
            inputEmail.requestFocus();
        } else {
            JSONObject params = new JSONObject();
            try {
                params.put("email", email);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String data = params.toString();
            String url = getString(R.string.url_api) + "/profile/edit";

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient http = new HttpClient(getActivity(), url);
                    http.setMethod("post");
                    http.setData(data);
                    http.setToken(true);
                    http.send();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Integer code = http.getStatusCode();
                            if (code == 200) {
                                startActivity(new Intent(getActivity(), ProfileFragment.class));
                                Toast.makeText(getActivity(), "Edit email success", Toast.LENGTH_SHORT).show();
                            } else if (code == 422) {
                                try {
                                    JSONObject response = new JSONObject(http.getResponse());
                                    String msg = response.getString("message");
                                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (code == 401) {
                                try {
                                    JSONObject response = new JSONObject(http.getResponse());
                                    String msg = response.getString("message");
                                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Error " + code, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        }
    }
}