package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TravelCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}