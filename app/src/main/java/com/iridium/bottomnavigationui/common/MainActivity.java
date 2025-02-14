package com.iridium.bottomnavigationui.common;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iridium.bottomnavigationui.homefeed.HomeFragment;
import com.iridium.bottomnavigationui.NewHouseFragment;
import com.iridium.bottomnavigationui.account.ProfileFragment;
import com.iridium.bottomnavigationui.R;

public class MainActivity extends AppCompatActivity
{
    Fragment editProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        editProfileFragment = getSupportFragmentManager().findFragmentByTag("EDIT_PROFILE_FRAGMENT");

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_new_home:
                            selectedFragment = new NewHouseFragment();
                            break;

                        case R.id.nav_account:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.nav_more:
                      //      selectedFragment = new ProfileFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}
