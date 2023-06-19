package com.example.medidoc_bts_h3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.R;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    //private final ArrayList<Doctor> listDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_doctor_name, item_doctor_spec, item_doctor_city;

        public ViewHolder(View view) {
            super(view);

            item_doctor_name = (TextView) view.findViewById(R.id.item_doctor_name);
            item_doctor_spec = (TextView) view.findViewById(R.id.item_doctor_spec);
            item_doctor_city = (TextView) view.findViewById(R.id.item_doctor_city);
        }

        public TextView getItem_doctor_name() {
            return item_doctor_name;
        }

        public TextView getItem_doctor_spec() {
            return item_doctor_spec;
        }

        public TextView getItem_doctor_city() {
            return item_doctor_city;
        }
    }

    public DoctorAdapter() {}


    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_home_doctor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
         holder.getItem_doctor_name().setText("Théo Meunier");
         holder.getItem_doctor_spec().setText("Généraliste");
         holder.getItem_doctor_city().setText("Paris");
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
