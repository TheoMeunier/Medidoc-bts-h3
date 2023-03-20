package com.example.medidoc_bts_h3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.models.User;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private final ArrayList<User> listDataSet;

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

    public DoctorAdapter(ArrayList<User> users) {
        this.listDataSet =  users;
    }


    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_home_doctor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
         //holder.getItem_home_title().setText(this.listDataSet);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}