package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddTravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}