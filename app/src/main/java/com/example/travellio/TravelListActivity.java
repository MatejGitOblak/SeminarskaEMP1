package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class TravelListActivity extends AppCompatActivity {
    RecyclerView recyclerViewTravelList;
    DBHelper myDB;
    ArrayList<String> travel_id, travel_name, travel_info, travel_datefrom, travel_dateto;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerViewTravelList = findViewById(R.id.recyclerViewTravelList);
        myDB = new DBHelper(TravelListActivity.this);
        travel_id = new ArrayList<>();
        travel_name = new ArrayList<>();
        travel_info = new ArrayList<>();
        travel_datefrom = new ArrayList<>();
        travel_dateto = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(TravelListActivity.this, travel_id, travel_name, travel_info, travel_datefrom, travel_dateto);
        recyclerViewTravelList.setAdapter(customAdapter);
        recyclerViewTravelList.setLayoutManager(new LinearLayoutManager(TravelListActivity.this));
    }

    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext()){
                travel_id.add(cursor.getString(0));
                travel_name.add(cursor.getString(1));
                travel_info.add(cursor.getString(2));
                travel_datefrom.add(cursor.getString(3));
                travel_dateto.add(cursor.getString(4));
            }
        }
    }
}