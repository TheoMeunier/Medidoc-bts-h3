package com.example.medidoc_bts_h3.fragment.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medidoc_bts_h3.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ProfileEditPasswordFragment extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_edit_password, container, false);
    }
}