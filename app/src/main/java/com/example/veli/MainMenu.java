package com.example.veli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.veli.DB.TravelsDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * An activity that handles the interactions between fragment and bottom navigation
 */


public class MainMenu extends AppCompatActivity {

    //Create the instance of dbHelper
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Initialize in home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        // Connect with bd
        dbHelper = new TravelsDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        /**
         * Bottom navigation: change the screen to the fragment selected.
         */
        BottomNavigationView bottomNav = findViewById(R.id.main_menu);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_form:
                    selectedFragment = new FormFragment(dbHelper, db);
                    break;
                case R.id.nav_list:
                    selectedFragment = new ListFragment(dbHelper, db);
                    break;
                case R.id.nav_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });
    }
    //Close the db when the activity onDestroy
    @Override
    protected void onDestroy() {
        dbHelper.close();
        db.close();
        super.onDestroy();
    }

}