package com.example.gym_polyakov;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.gym_polyakov.ui.lessons.LessonsFragment;
import com.example.gym_polyakov.ui.profile.ProfileFragment;
import com.example.gym_polyakov.ui.plan.PlanFragment;
import com.example.gym_polyakov.ui.reports.ReportsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BottomNavigationMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navigation_plan:
                        Log.e("itemID", menuItem.toString() + "  ASDANDASD  " + menuItem.getItemId());
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();
                        return true;
                    case R.id.navigation_lessons:
                        Log.e("itemID", menuItem.toString() + "  ASDANDASD  " + menuItem.getItemId());
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new LessonsFragment()).commit();
                        return true;
                    case R.id.navigation_reports:
                        Log.e("itemID", menuItem.toString() + "  ASDANDASD  " + menuItem.getItemId());
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ReportsFragment()).commit();
                        return true;
                    case R.id.navigation_profile:
                        Log.e("itemID", menuItem.toString() + "  ASDANDASD  " + menuItem.getItemId());
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ProfileFragment()).commit();
                        return true;
                }
                return true;
            }
        });

    }

}
