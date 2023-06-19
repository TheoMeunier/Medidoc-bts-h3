package com.example.medidoc_bts_h3.adapter.appointment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.models.AppointementFuture;
import com.example.medidoc_bts_h3.models.Doctor;

import java.util.ArrayList;

public class AppointementFuturAdapter extends RecyclerView.Adapter<AppointementFuturAdapter.ViewHolder> {

    //private final ArrayList<AppointementFuture> listDataSet;
    //TextView date, doctor_name, doctor_spe, patient_name;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //On récupère tout nos élements
        private final TextView doctor_name;

        public ViewHolder(View view) {
            super(view);

            doctor_name = (TextView) view.findViewById(R.id.card_medical_doctor_name);
        }

        public TextView getDoctor_name() {
            return doctor_name;
        }
    }

    public AppointementFuturAdapter() {
        //this.listDataSet =  futures;
    }


    //Permet Injecter notre layout
    @NonNull
    @Override
    public AppointementFuturAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on lui donne le layout sur le quel utiliser
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_appointmenet, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointementFuturAdapter.ViewHolder holder, int position) {}

    //Return le nombtre items
    @Override
    public int getItemCount() {
        return 5;
    }
}
