package com.example.gym_polyakov;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.gym_polyakov.ui.LessonsFragment;
import com.example.gym_polyakov.ui.ProfileFragment;
import com.example.gym_polyakov.ui.PlanFragment;
import com.example.gym_polyakov.ui.ReportsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BottomNavigationMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);
        startService(new Intent(getApplicationContext(), Gym_service.class));
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        startService(new Intent(this, MyServiceTR.class));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navigation_plan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();
                        break;
                    case R.id.navigation_lessons:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new LessonsFragment()).commit();
                        break;
                    case R.id.navigation_reports:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ReportsFragment()).commit();
                        break;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ProfileFragment()).commit();
                        break;
                }
                return true;
            }
        });

    }

}
