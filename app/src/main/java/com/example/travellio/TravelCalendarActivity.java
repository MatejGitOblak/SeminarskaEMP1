package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TravelCalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    DBHelper myDB;
    ArrayList<String> travel_id, travel_name, travel_info, travel_datefrom, travel_dateto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        CalendarView simpleCalendarView = findViewById(R.id.calendarView);
        myDB = new DBHelper(TravelCalendarActivity.this);
        travel_id = new ArrayList<>();
        travel_name = new ArrayList<>();
        travel_info = new ArrayList<>();
        travel_datefrom = new ArrayList<>();
        travel_dateto = new ArrayList<>();

        storeDataInArrays();

        MaterialDatePicker.Builder<Pair<Long, Long>> builderRange = MaterialDatePicker.Builder.dateRangePicker();
        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();


        for (int i = 0; i < travel_datefrom.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateFrom = null;
            Date dateTo = null;
            try {
                dateFrom = formatter.parse(travel_datefrom.get(i));
                dateTo = formatter.parse(travel_dateto.get(i));

                long from = dateFrom.getTime();
                long to = dateTo.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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