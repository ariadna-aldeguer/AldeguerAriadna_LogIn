package com.example.veli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity class that retrieve from the user the credentials
 * to get into the application.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtLogin = findViewById(R.id.txtLogin);
        Button btnSigIn = findViewById(R.id.btnAdd);
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);

        // A sleep to see the splash screen
        SystemClock.sleep(500);
        setTheme(R.style.Theme_Veli);

        /**
         * When there is a click on the "Login" button and the credentials are correct,
         * home screen will take place.
         */
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                if(txtUserName.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                } else {
                    Toast.makeText(getApplicationContext(), "The password is incorrect", Toast.LENGTH_LONG).show();
                    txtPassword.setText("");
                }

            }
        });
    }
}