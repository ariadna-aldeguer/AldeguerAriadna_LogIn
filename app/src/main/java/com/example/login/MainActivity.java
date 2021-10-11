package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Busca a Res per id
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtLogin = findViewById(R.id.txtLogin);
        Button btnSigIn = findViewById(R.id.btnAdd);
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        // Això es perque es mostri el splash screen
        SystemClock.sleep(200);
        setTheme(R.style.Theme_Login);


        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                if(txtUserName.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    Log.i("Test", "Log in Correcte");
                } else {
                    TextView lblLoginResult = findViewById(R.id.lblLoginResult);
                    Log.i("Test", "Log in Incorrecte");
                }

            }
        });
    }
}