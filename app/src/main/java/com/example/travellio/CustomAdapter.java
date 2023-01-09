package com.example.travellio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList travel_id, travel_name, travel_info, travel_datefrom, travel_dateto;

    CustomAdapter(Context context, ArrayList travel_id, ArrayList travel_name, ArrayList travel_info, ArrayList travel_datefrom, ArrayList travel_dateto)
    {
        this.context = context;
        this.travel_id = travel_id;
        this.travel_name = travel_name;
        this.travel_info = travel_info;
        this.travel_datefrom = travel_datefrom;
        this.travel_dateto = travel_dateto;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView travel_id_txt, travel_name_txt, travel_datefrom_txt, travel_dateto_txt;
        LinearLayout details_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            travel_id_txt = itemView.findViewById(R.id.travel_id_txt);
            travel_name_txt = itemView.findViewById(R.id.travel_name_txt);
            travel_datefrom_txt = itemView.findViewById(R.id.travel_datefrom_txt);
            travel_dateto_txt = itemView.findViewById(R.id.travel_dateto_txt);
            details_layout = itemView.findViewById(R.id.details_layout);
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
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.travel_id_txt.setText(String.valueOf(travel_id.get(position)));
        holder.travel_name_txt.setText(String.valueOf(travel_name.get(position)));
        holder.travel_datefrom_txt.setText(String.valueOf(travel_datefrom.get(position)));
        holder.travel_dateto_txt.setText(String.valueOf(travel_dateto.get(position)));
        holder.details_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TravelDetailsActivity.class);
                for (int i = 0; i < travel_id.size(); i++) {

                    if(i == position) {
                        Travel travel = HelperFunctions.makeClassFromString((String) travel_info.get(position));

                        intent.putExtra("airport", String.valueOf(travel.airport));
                        intent.putExtra("flightPrice", String.valueOf(travel.flightPrice));
                        intent.putExtra("flightTo", String.valueOf(travel.flightTo));
                        intent.putExtra("flightBack", String.valueOf(travel.flightBack));
                        intent.putExtra("name", String.valueOf(travel.stays.get(0).name));
                        intent.putExtra("stayPrice", String.valueOf(travel.stays.get(0).price));
                        intent.putExtra("stayTo", String.valueOf(travel.stays.get(0).to));
                        intent.putExtra("stayFrom", String.valueOf(travel.stays.get(0).from));
                        intent.putExtra("country", String.valueOf(travel.stays.get(0).country));
                        intent.putExtra("city", String.valueOf(travel.stays.get(0).city));
                        intent.putExtra("street", String.valueOf(travel.stays.get(0).street));
                        intent.putExtra("streetNum", String.valueOf(travel.stays.get(0).streetNumber));
                    }
                }
                System.out.println("Koncano pisanje date");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travel_id.size();
    }
}
