package com.example.levi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.levi.DB.TravelsDBHelper;

import java.util.ArrayList;


import com.example.levi.Model.Travel;
/**
 * A fragment subclass that display list of travels
 * using a recycler view and grabbing data from the database.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Create the instance of dbHelper
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    //Create the instance of listFragment
    private Fragment listFragment;


    /**
     * Empty constructor of class ListFragment
     */
    public ListFragment() {
    }

    /**
     * Constructor of class ListFragment
     */
    public ListFragment(TravelsDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
        this.listFragment = this;
    }

    /**
     * Create a new instance of ListFragment
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of the fragment.
     * @param savedInstanceState: instance of ListFragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    /**
     * Creates and returns the view for the lit fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Travel> array_travel = dbHelper.getTravels(db);
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dbHelper, db, array_travel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));

        Button buttonDeleteAll = view.findViewById(R.id.btnDeleteAll);

        /**
         * When there is a click on the "Delete all travels" button,
         * all the travels will be delete from database.
         * A confirmation toast will appear.
         */
        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("All travels will be delete permanently");
                builder.setMessage("Do you want to continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //OK METHOD
                                dbHelper.deleteAllTravels(db);
                                refresh();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //KO METHOD
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    /**
     * Fragment screen it refresh.
     */
    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(ListFragment.this).attach(ListFragment.this).commit();
    }
}