package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Home);

        // Busca a Res per id
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtLogin = findViewById(R.id.txtLogin);
        Button btnSigIn = findViewById(R.id.btnAdd);
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);
        BottomNavigationView bottomNav = findViewById(R.id.main_menu);

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_form:
                    selectedFragment = new FormFragment();
                    break;
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_list:
                    selectedFragment = new ListFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                if(txtUserName.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    Log.i("Test", "Log in Correcte");
                } else {
                    Log.i("Test", "Log in Incorrecte");
                }


            }
        });

    }
}