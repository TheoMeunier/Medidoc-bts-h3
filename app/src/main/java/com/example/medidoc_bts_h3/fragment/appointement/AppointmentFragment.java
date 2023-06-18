package com.example.medidoc_bts_h3.fragment.appointement;

import android.icu.lang.UScript;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.medidoc_bts_h3.R;
import com.example.medidoc_bts_h3.adapter.appointment.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.io.Console;
import java.util.Objects;

public class AppointmentFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewpager;
    ViewPagerAdapter viewPagerAdapter;

    public AppointmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        tabLayout = view.findViewById(R.id.tablelayout);
        viewpager = view.findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(this.requireActivity());

        System.out.println("hello");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                System.out.println(tab);
                System.out.println("hello1");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.out.println("hello2");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.out.println("hello3");
            }

        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return view;
    }
}
