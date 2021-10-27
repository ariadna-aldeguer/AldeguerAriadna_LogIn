package com.example.login;

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
import android.widget.ImageButton;

import com.example.login.DB.TravelsDBHelper;

import java.util.ArrayList;


import com.example.login.Model.Travel;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    public ListFragment() {
        // Required empty public constructor
    }

    public ListFragment(TravelsDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<Travel> array_travel = dbHelper.getTravels(db);
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(dbHelper, db, array_travel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));

        Button buttonDropAll = view.findViewById(R.id.btnDropAll);
        buttonDropAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("All travels will be deleted permanently");
                builder.setMessage("Do you want to continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.dropAllTravels(db);
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

        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }
    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(ListFragment.this).attach(ListFragment.this).commit();
    }
}