package com.example.medidoc_bts_h3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.medidoc_bts_h3.databinding.ActivityMainBinding;
import com.example.medidoc_bts_h3.fragment.HomeFragment;
import com.example.medidoc_bts_h3.fragment.ProfileFragment;
import com.example.medidoc_bts_h3.fragment.SettingsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemReselectedListener( item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.setting:
                    replaceFragment(new SettingsFragment());
                    break;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}