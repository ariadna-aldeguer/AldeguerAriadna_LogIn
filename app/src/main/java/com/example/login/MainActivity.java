package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search on Res and display the items view by id
        Button btnSigIn = findViewById(R.id.btnSignIn);
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);

        //Assign a click listener to the button
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                // To see test go > Logtcat > Filter by @tag ("Test")
                if(txtUserName.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    Log.i("Test", "Log in Correcte");
                } else {
                    Log.i("Test", "Log in Incorrecte");
                }
            }
        });
    }
}