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
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailTravelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailTravelFragment extends Fragment {

    // Create the instance of dbHelper
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Empty constructor of class FormFragment
     */
    public DetailTravelFragment() {
        // Required empty public constructor
    }

    /**
     * Constructor of class FormFragment
     */
    public DetailTravelFragment(TravelsDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailTravelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailTravelFragment newInstance(String param1, String param2) {
        DetailTravelFragment fragment = new DetailTravelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of the fragment.
     * @param savedInstanceState: instance of Detail fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates and returns the view for the detail fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_travel, container, false);

        Bundle bundle = getArguments();
        Travel travel = (Travel) bundle.getSerializable("Travel");

        EditText country = view.findViewById(R.id.txtCountryEdit);
        country.setText(travel.getCountry());
        EditText city = view.findViewById(R.id.txtCityEdit);
        city.setText(travel.getCity());
        EditText airport = view.findViewById(R.id.txtAirportEdit);
        airport.setText(travel.getAirport());


        /**
         * Button to edit and save. Edit option allows to modify the travel information.
         * After saving, a confirmation toast will appear.
         */
        Button btnDetail = view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnDetail.getText().equals(getString(R.string.btnEdit))) {
                    enable(country, city, airport, true);
                    btnDetail.setText(getString(R.string.btnSave));
                } else if (btnDetail.getText().equals(getString(R.string.btnSave))){
                    enable(country, city, airport, false);
                    btnDetail.setText(getString(R.string.btnEdit));

                    String co = country.getText().toString();
                    String ci = city.getText().toString();
                    String ai = airport.getText().toString();

                    Travel newTravel = new Travel(co, ci, ai);

                    if (co.equals("") && ci.equals("") && ai.equals("")) {
                        Toast.makeText(getContext(), getString(R.string.property), Toast.LENGTH_LONG).show();
                    } else {
                        dbHelper.updateTravel(db, travel, newTravel);
                        Toast.makeText(getContext(), getString(R.string.save), Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        return view;
    }

    /**
     * Fields state will change to enable or disable so they can be modify.
     * @param country
     * @param city
     * @param airport
     * @param change
     */

    public static void enable(EditText country, EditText city, EditText airport, boolean change){
        country.setEnabled(change);
        city.setEnabled(change);
        airport.setEnabled(change);
    }
}