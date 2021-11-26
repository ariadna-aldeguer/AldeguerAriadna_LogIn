package com.example.veli;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veli.DB.TravelsDBHelper;
import com.example.veli.Model.Travel;

/**
 * A fragment subclass containing a form to add information into the database.
 */
public class FormFragment extends Fragment {

    // Create the instance of dbHelper
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Empty constructor of class FormFragment
     */
    public FormFragment() {
    }

    /**
     * Constructor of class FormFragment
     */
    public FormFragment(TravelsDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Create a new instance of FormFragment
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of the fragment.
     * @param savedInstanceState: instance of form fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates and returns the view for the form fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);

        EditText country = view.findViewById(R.id.txtInputCountry);
        EditText city = view.findViewById(R.id.txtInputCity);
        EditText airport = view.findViewById(R.id.txtInputAirport);
        Button button = view.findViewById(R.id.btnSingIn);

        /**
         * When there is a click on the "Add travel" button, the travels is added into the database.
         * If succeeded, a confirmation toast will appear, otherwise an error message will be show.
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String co = country.getText().toString();
                String ci = city.getText().toString();
                String ai = airport.getText().toString();
                if (co.equals("") && ci.equals("") && ai.equals("")) {
                    Toast.makeText(getContext(), getString(R.string.errorProperties), Toast.LENGTH_LONG).show();
                } else {
                    Travel travel = new Travel(co, ci, ai);
                    dbHelper.insertTravel(db, travel);
                    Toast.makeText(getContext(), getString(R.string.save), Toast.LENGTH_LONG).show();
                }
                country.setText("");
                city.setText("");
                airport.setText("");
            }
        });

        return view;
    }
}