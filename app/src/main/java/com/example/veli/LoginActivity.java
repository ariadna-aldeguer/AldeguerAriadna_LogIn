package com.example.veli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
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
        // A sleep to see the splash screen
        SystemClock.sleep(200);
        setTheme(R.style.Theme_Veli);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btnSigIn = findViewById(R.id.btnSingIn);
        EditText textMail = findViewById(R.id.txtMail);
        EditText txtPassword = findViewById(R.id.txtPassword);



        // If the user already access into the app, goes directly to home screen
        SharedPreferences prefs = getSharedPreferences("SharedP", Context.MODE_PRIVATE);

        if(prefs.getBoolean("login", false)) startHome();


        /**
         * When there is a click on the "Login" button and the credentials are correct,
         * home screen will take place.
         */
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(textMail.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    savePreferences(prefs, textMail.getText().toString());
                    startHome();
                } else {
                    Toast.makeText(getApplicationContext(), "The password is incorrect", Toast.LENGTH_LONG).show();
                    txtPassword.setText("");
                }
            }
        });
    }

    /**
     * Start activity Main menu
     */
    public void startHome(){
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }

    /**
     * Save preferences into SharedPreferences
     * @param prefs: instance of SP
     * @param mail: user
     */
    public void savePreferences(SharedPreferences prefs, String mail) {
        SharedPreferences.Editor prefsEdits = prefs.edit();

        prefsEdits.putString("mail", mail);
        prefsEdits.putBoolean("login", true);

        prefsEdits.commit();
    }
}