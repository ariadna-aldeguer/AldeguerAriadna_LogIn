package com.example.veli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.veli.DB.TravelsDBHelper;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

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
        SharedPreferences prefs = getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        //setAppLocale(prefs.getString("language", ""));;
        Log.i("error", "hola" + prefs.getString("language", ""));
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

        /*BottomAppBar upMenu = findViewById(R.id.up_menu);
        upMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.logout:
                    //TODO
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });*/
        Log.i("exsh", prefs.getString("mail", "provaholamarta"));


    }


    //Close the db when the activity onDestroy
    @Override
    protected void onDestroy() {
        dbHelper.close();
        db.close();
        super.onDestroy();
    }

    /**
     * Change the language of the applications
     * @param localeCode: language
     */
    protected void setAppLocale(String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(config, dm);
    }
}