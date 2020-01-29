package com.example.gym_polyakov;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gym_polyakov.ui.home.LessonsFragment;
import com.example.gym_polyakov.ui.notifications.ProfileFragment;
import com.example.gym_polyakov.ui.plan.PlanFragment;
import com.example.gym_polyakov.ui.reports.ReportsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BottomNavigationMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
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
