package com.example.login;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.DB.TravelsDBHelper;
import com.example.login.Model.Travel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Travel> array_travel;
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    public RecyclerViewAdapter(ArrayList<Travel> arrN){
        array_travel = arrN;
    }
    public interface RecyclerViewClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

    public RecyclerViewAdapter(TravelsDBHelper dbHelper, SQLiteDatabase db, ArrayList<Travel> arrN) {
        array_travel = arrN;
        this.dbHelper = dbHelper;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCity.setText(array_travel.get(position).getCity());
        holder.txtAirport.setText(array_travel.get(position).getAirport());
        holder.txtCountry.setText(array_travel.get(position).getCountry());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = array_travel.get(position).getId();
                dbHelper.deleteTravel(db, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array_travel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCity;
        TextView txtCountry;
        TextView txtAirport;
        ImageView imgList;
        Button btnDelete;
        Button btnEdit;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtCity = view.findViewById(R.id.txtCityList);
            txtCountry = view.findViewById(R.id.txtCountryList);
            txtAirport = view.findViewById(R.id.txtAirportList);
            imgList = view.findViewById(R.id.imgList);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnEdit = view.findViewById(R.id.btnEdit);
        }
    }
    public Object getMyPos(int pos){
        return array_travel.get(pos);
    }
}

