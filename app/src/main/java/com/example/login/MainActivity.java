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

        // Busca a Res per id
        Button btnSigIn = findViewById(R.id.btnSignIn);
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);

        //Agafat de la documentació d'android: Li estem dient que miri si al botó "escolta" algo
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // Per veure els logs: Logcat/ Filtrar pel tag/ Seleccionar bé l'emulador
                // Log.i("Test", "Has fet click");

                if(txtUserName.getText().toString().equals("123") && txtPassword.getText().toString().equals("123")){
                    Log.i("Test", "Log in Correcte");
                } else {
                    Log.i("Test", "Log in Incorrecte");
                }
            }
        });
    }
}