package com.example.medidoc_bts_h3.fragment.appointement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medidoc_bts_h3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointementPastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointementPastFragment extends Fragment {

    public AppointementPastFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AppointementPastFragment newInstance(String param1, String param2) {
        return new AppointementPastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointement_past, container, false);

        return view;
    }
}