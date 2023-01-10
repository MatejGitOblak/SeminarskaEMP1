package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class TravelHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerViewTravelHistory;
    DBHelper myDB;
    ArrayList<String> travel_id, travel_name, travel_info, travel_datefrom, travel_dateto;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerViewTravelHistory = findViewById(R.id.recyclerViewTravelHistory);
        myDB = new DBHelper(TravelHistoryActivity.this);
        travel_id = new ArrayList<>();
        travel_name = new ArrayList<>();
        travel_info = new ArrayList<>();
        travel_datefrom = new ArrayList<>();
        travel_dateto = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(TravelHistoryActivity.this, travel_id, travel_name, travel_info, travel_datefrom, travel_dateto);
        recyclerViewTravelHistory.setAdapter(customAdapter);
        recyclerViewTravelHistory.setLayoutManager(new LinearLayoutManager(TravelHistoryActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllDataHistory();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                travel_id.add(cursor.getString(0));
                travel_name.add(cursor.getString(1));
                travel_info.add(cursor.getString(2));
                travel_datefrom.add(cursor.getString(3));
                travel_dateto.add(cursor.getString(4));
            }
        }
    }
}