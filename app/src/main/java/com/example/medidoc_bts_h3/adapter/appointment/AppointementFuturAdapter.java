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

    private final ArrayList<AppointementFuture> listDataSet;
    TextView date, doctor_name, doctor_spe, patient_name;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_home_title;

        public ViewHolder(View view) {
            super(view);

            item_home_title = (TextView) view.findViewById(R.id.card_medical_doctor_name);
        }

        public TextView getItem_home_title() {
            return item_home_title;
        }
    }

    public AppointementFuturAdapter(ArrayList<AppointementFuture> futures) {
        this.listDataSet =  futures;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on lui donne le layout sur le quel utiliser
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_appointmenet, parent, false);

        date = view.findViewById(R.id.rdv_date);
        doctor_name = view.findViewById(R.id.rdv_doctor_name);
        doctor_spe = view.findViewById(R.id.rdv_doctor_spce);
        //patient_name = view.findViewById(R.id.rdv_patient);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         //holder.getItem_home_title().setText(listDataSet.get(position).getDate().get);
         //holder.getItem_home_title().setText(listDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
