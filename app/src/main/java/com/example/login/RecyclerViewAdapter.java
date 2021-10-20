package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Travel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Travel> array_travel;

    public RecyclerViewAdapter(ArrayList<Travel> arrN){
        array_travel = arrN;
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

        public ViewHolder(@NonNull View view) {
            super(view);
            txtCity = view.findViewById(R.id.txtCityList);
            txtCountry = view.findViewById(R.id.txtCountryList);
            txtAirport = view.findViewById(R.id.txtAirportList);
            imgList = view.findViewById(R.id.imgList);
        }
    }
}

