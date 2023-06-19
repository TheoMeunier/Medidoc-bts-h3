package com.example.medidoc_bts_h3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.Activity.Doctor.DoctorShowActivity;
import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.models.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private  final ArrayList<Doctor> listDataSet;
    LayoutInflater context;

    public DoctorAdapter(Context context, ArrayList<Doctor> data) {
        this.context = LayoutInflater.from(context);
        this.listDataSet = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_doctor_name, item_doctor_spec, item_doctor_city;

        public ViewHolder(View view) {
            super(view);

            item_doctor_name = (TextView) view.findViewById(R.id.item_doctor_name);
            item_doctor_spec = (TextView) view.findViewById(R.id.item_doctor_spec);
            item_doctor_city = (TextView) view.findViewById(R.id.item_doctor_city);


            // On rend la card cliquable pour aller voir les informations du médecins
            view.findViewById(R.id.btn_doctor_show).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Integer doctorId = listDataSet.get(position).getId();

                    Intent intent = new Intent(v.getContext(), DoctorShowActivity.class);
                    intent.putExtra("id", doctorId.toString());
                    v.getContext().startActivity(intent);
                }
            });
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

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = context
                .inflate(R.layout.items_home_doctor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
         holder.getItem_doctor_name().setText(listDataSet.get(position).getFirst_name() + " " + listDataSet.get(position).getLast_name());
         holder.getItem_doctor_spec().setText(listDataSet.get(position).getSpeciality());
         holder.getItem_doctor_city().setText(listDataSet.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return listDataSet.size();
    }
}
