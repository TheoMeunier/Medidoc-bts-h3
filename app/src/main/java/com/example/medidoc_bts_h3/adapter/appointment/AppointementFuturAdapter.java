package com.example.medidoc_bts_h3.adapter.appointment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.fragment.appointement.AppointementFutureFragment;
import com.example.medidoc_bts_h3.models.AppointementFuture;
import com.example.medidoc_bts_h3.models.Doctor;

import java.util.ArrayList;

public class AppointementFuturAdapter extends RecyclerView.Adapter<AppointementFuturAdapter.ViewHolder> {

    private final ArrayList<AppointementFuture> listDataSet;
    LayoutInflater context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rdv_date, rdv_doctor_name,  rdv_doctor_spce, rdv_patient;

        public ViewHolder(View view) {
            super(view);

            rdv_date = (TextView) view.findViewById(R.id.rdv_date);
            rdv_doctor_name = (TextView) view.findViewById(R.id.rdv_doctor_name);
            rdv_doctor_spce = (TextView) view.findViewById(R.id.rdv_doctor_spce);
            rdv_patient = (TextView) view.findViewById(R.id.rdv_patient);
        }

        public TextView getRdv_date() {
            return rdv_date;
        }

        public TextView getRdv_doctor_name(){
            return rdv_doctor_name;
        }

        public TextView getRdv_doctor_spce() {
            return rdv_doctor_spce;
        }

        public TextView getRdv_patient() {
            return rdv_patient;
        }
    }

    public AppointementFuturAdapter(Context context, ArrayList<AppointementFuture> data) {
        this.context = LayoutInflater.from(context);
        this.listDataSet = data;
    }


    //Permet Injecter notre layout
    @NonNull
    @Override
    public AppointementFuturAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on lui donne le layout sur le quel utiliser
        View view = context
                .inflate(R.layout.items_appointmenet, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointementFuturAdapter.ViewHolder holder, int position) {
        holder.getRdv_date().setText(listDataSet.get(position).getDate());
        holder.getRdv_doctor_name().setText(listDataSet.get(position).getDoctor().getFirst_name() + " " + listDataSet.get(position).getDoctor().getLast_name());
        holder.getRdv_doctor_spce().setText(listDataSet.get(position).getDoctor().getSpeciality());
        holder.getRdv_patient().setText(listDataSet.get(position).getPatient());
    }

    //Return le nombtre items
    @Override
    public int getItemCount() {
        return listDataSet.size() ;
    }
}
