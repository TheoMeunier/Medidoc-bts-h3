package com.example.medidoc_bts_h3.adapter.appointment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.medidoc_bts_h3.fragment.appointement.AppointementFutureFragment;
import com.example.medidoc_bts_h3.fragment.appointement.AppointementPastFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new AppointementFutureFragment();
            case 1: return new AppointementPastFragment();
            default: return  new AppointementFutureFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
