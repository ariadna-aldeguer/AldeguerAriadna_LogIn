package com.example.veli;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veli.DB.TravelsDBHelper;
import com.example.veli.Model.Travel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RecyclerView that will display a list of travels.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Travel> array_travel;
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;
    private Fragment fragment;

    public RecyclerViewAdapter(ArrayList<Travel> arrN){
        array_travel = arrN;
    }

    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

    /**
     * Constructor of RecyclerViewAdapter
     */
    public RecyclerViewAdapter(Fragment fragment, TravelsDBHelper dbHelper, SQLiteDatabase db, ArrayList<Travel> arrN) {
        this.fragment = fragment;
        array_travel = arrN;
        this.dbHelper = dbHelper;
        this.db = db;
    }


    /**
     * Creates a new ViewHolder
     * @param parent:  layout manager
     * @param viewType
     * @return holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Associate a ViewHolder with data.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtCity.setText(array_travel.get(position).getCity());
        holder.txtAirport.setText(array_travel.get(position).getAirport());
        holder.txtCountry.setText(array_travel.get(position).getCountry());

        /**
         * This bottom will delete a travel on the db.
         */
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = array_travel.get(position).getId();
                dbHelper.deleteTravel(db, id);
                ListFragment list = (ListFragment) fragment.getFragmentManager().findFragmentById(R.id.fragment_container);
                list.refresh();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity app = (AppCompatActivity) view.getContext();
                DetailTravelFragment detailFragment = new DetailTravelFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Travel", array_travel.get(position));

                detailFragment.setArguments(bundle);

                app.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).commit();

            }
        });


    }

    /**
     * Get the size of the data set.
     * @return nº items
     */
    @Override
    public int getItemCount() {
        return array_travel.size();
    }

    /**
     * Links the elements of item_list with the RecyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtCity;
        TextView txtCountry;
        TextView txtAirport;
        ImageView imgList;
        ImageButton btnDelete;
        Button btnEdit;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtCity = view.findViewById(R.id.txtCityList);
            txtCountry = view.findViewById(R.id.txtCountryList);
            txtAirport = view.findViewById(R.id.txtAirportList);
            imgList = view.findViewById(R.id.imgList);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }
}

