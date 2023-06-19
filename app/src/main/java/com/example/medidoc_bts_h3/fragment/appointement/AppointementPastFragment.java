package com.example.medidoc_bts_h3.fragment.appointement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.adapter.appointment.AppointementFuturAdapter;
import com.example.medidoc_bts_h3.adapter.appointment.AppointementPastAdapter;

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

        RecyclerView past = view.findViewById(R.id.recyclerViewRdvPast);
        past.setAdapter(new AppointementPastAdapter());

        return view;
    }
}