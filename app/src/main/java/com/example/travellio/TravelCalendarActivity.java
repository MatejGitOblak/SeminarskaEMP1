package com.example.travellio;

<<<<<<< HEAD
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;

public class TravelCalendarActivity extends AppCompatActivity {

    DBHelper myDB;
    ArrayList<String> travel_id, travel_name, travel_info, travel_datefrom, travel_dateto;

=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TravelCalendarActivity extends AppCompatActivity {

>>>>>>> parent of bf5c2ca (n)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_calendar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
<<<<<<< HEAD

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.setSelectionMode (MaterialCalendarView.SELECTION_MODE_NONE);

        myDB = new DBHelper(TravelCalendarActivity.this);
        travel_id = new ArrayList<>();
        travel_name = new ArrayList<>();
        travel_info = new ArrayList<>();
        travel_datefrom = new ArrayList<>();
        travel_dateto = new ArrayList<>();

        storeDataInArrays();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 15);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2023, 1, 20);

        materialCalendarView.setDateSelected(calendar, true);
        materialCalendarView.setDateSelected(calendar1, true);

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
=======
>>>>>>> parent of bf5c2ca (n)
    }
}