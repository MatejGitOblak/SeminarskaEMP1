package com.example.travellio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList travel_id, travel_name, travel_datefrom, travel_dateto;

    CustomAdapter(Context context, ArrayList travel_id, ArrayList travel_name, ArrayList travel_info, ArrayList travel_datefrom, ArrayList travel_dateto)
    {
        this.context = context;
        this.travel_id = travel_id;
        this.travel_name = travel_name;
        this.travel_datefrom = travel_datefrom;
        this.travel_dateto = travel_dateto;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView travel_id_txt, travel_name_txt, travel_datefrom_txt, travel_dateto_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            travel_id_txt = itemView.findViewById(R.id.travel_id_txt);
            travel_name_txt = itemView.findViewById(R.id.travel_name_txt);
            travel_datefrom_txt = itemView.findViewById(R.id.travel_datefrom_txt);
            travel_dateto_txt = itemView.findViewById(R.id.travel_dateto_txt);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_travel_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.travel_id_txt.setText(String.valueOf(travel_id.get(position)));
        holder.travel_name_txt.setText(String.valueOf(travel_name.get(position)));
        holder.travel_datefrom_txt.setText(String.valueOf(travel_datefrom.get(position)));
        holder.travel_dateto_txt.setText(String.valueOf(travel_dateto.get(position)));
    }

    @Override
    public int getItemCount() {
        return travel_id.size();
    }
}
