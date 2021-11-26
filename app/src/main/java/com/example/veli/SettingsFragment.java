package com.example.veli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.Executor;

/**
 * A fragment subclass for settings screen.
 */

public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    private Spinner spinner;

    /**
     * Empty constructor of class FormFragment
     */
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of SettingsFragment
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of the fragment.
     * @param savedInstanceState: instance of setting fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates and returns the view for the form fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //Retrieve and hold the contents of the preferences file 'name'.
        SharedPreferences prefs = getContext().getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        //Modifying values in a SharedPreferences object
        SharedPreferences.Editor prefsEdit = prefs.edit();

        // Define Biometric Prompt and all possible answers
        executor = ContextCompat.getMainExecutor(getContext());
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            //Called when an unrecoverable error has been encountered and the operation is complete.
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getContext(), getString(R.string.errorAuthentication) + errString, Toast.LENGTH_LONG).show();
            }
            //Called when a biometric is recognized. Delete all SharedPreferences and go back to firts screen.
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Succeeded
                prefsEdit.clear().commit();
                startLogin();
            }
            //Called when a biometric is valid but not recognized.
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getContext(), getString(R.string.failedAuthentication), Toast.LENGTH_LONG).show();
            }
        });

        //Set options for the BiometricPrompt
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometricTitle))
                .setSubtitle(getString(R.string.biometricSubtitle))
                .setNegativeButtonText(getString(R.string.biometricPassword))
                .build();


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.languages,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);

        /**
         * The selected language it's saved in SharedPreferences.
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                if(position == 1){
                    prefs.edit().putString("language", "en").commit();
                } else if (position == 2){
                    prefs.edit().putString("language", "es").commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        Button btnSavePreferences = view.findViewById(R.id.btnSavePreferences);
        /**
         * Save the created preferences.
         */
        btnSavePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenu)getActivity()).setAppLocale(prefs.getString("language", ""));
                refresh();
            }
        });

        /**
         * On click reset button,  the biometric prompt is shown to authenticate.
         */
        Button btnReset = view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
        return view;
    }

    /**
     * Fragment screen it refresh.
     */
    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(SettingsFragment.this).attach(SettingsFragment.this).commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {    }

    /**
     * Start activity Login
     */
    public void startLogin() { startActivity(new Intent(getContext(), LoginActivity.class)); }
}