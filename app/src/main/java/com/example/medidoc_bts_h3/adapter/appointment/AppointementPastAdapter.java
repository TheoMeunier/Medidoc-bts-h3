package com.example.medidoc_bts_h3.adapter.appointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.models.AppointementFuture;
import com.example.medidoc_bts_h3.models.AppointementPast;

import java.util.ArrayList;

public class AppointementPastAdapter extends RecyclerView.Adapter<AppointementPastAdapter.ViewHolder> {

    private final ArrayList<AppointementPast> listDataSet;

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

    public AppointementPastAdapter(ArrayList<AppointementPast> past) {
        this.listDataSet =  past;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_home_doctor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         //holder.getItem_home_title().setText(listDataSet.get(position).name);
        //holder.getItem_home_title().setText(listDataSet.get(position).name());
    }

    @Override
    public int getItemCount() {
        return listDataSet.size();
    }
}
