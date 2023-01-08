package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.io.Serializable;

class Stay implements Serializable {

    public final String name, from, to, country, city, street;
    public final int streetNumber;
    public final double price;

    Stay(String name, String from, String to, String country, String city, String street, int streetNumber, double price) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.price = price;
    }
}

class Travel implements Serializable {

    public ArrayList<Stay> stays;

    public String flightTo, flightBack, airport;
    public double flightPrice;


    Travel() {
        this.flightTo = null;
        this.flightBack = null;
        this.flightPrice = 0.0;
        this.airport = null;
        this.stays = new ArrayList<Stay>();
    }

    public void addFlight(String flightTo, String flightBack, double flightPrice, String airport) {
        this.flightTo = flightTo;
        this.flightBack = flightBack;
        this.flightPrice = flightPrice;
        this.airport = airport;
    }

    public void addStay(String name, String from, String to, String country, String city, String street, int streetNumber, double price) {
        this.stays.add(new Stay(name, from, to, country, city, street, streetNumber, price));
    }
}


public class AddTravelActivity extends AppCompatActivity {

    ArrayList<String> travel_id, travel_name, travel_info;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button addTravel = findViewById(R.id.buttonAddTravel);
        Button addStay = findViewById(R.id.buttonAddStay);
        Button addFlight = findViewById(R.id.buttonAddFlight);

        EditText airportInput = findViewById(R.id.textInputAirport);
        EditText flightPriceInput = findViewById(R.id.textInputFlightPrice);
        EditText flightFromInput = findViewById(R.id.textInputFlightFrom);
        EditText flightToInput = findViewById(R.id.textInputFlightTo);

        EditText stayNameInput = findViewById(R.id.textInputStayName);
        EditText stayPriceInput = findViewById(R.id.textInputStayPrice);
        EditText stayFromInput = findViewById(R.id.textInputStayFrom);
        EditText stayToInput = findViewById(R.id.textInputStayTo);
        EditText stayCountyInput = findViewById(R.id.textInputStayCountry);
        EditText stayCityInput = findViewById(R.id.textInputStayCity);
        EditText stayStreetInput = findViewById(R.id.textInputStayStreet);
        EditText stayStreetNumberInput = findViewById(R.id.textInputStayStreetNumber);

        Travel t = new Travel();

        addFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(airportInput.getText())
                        && !TextUtils.isEmpty(flightFromInput.getText())
                        && !TextUtils.isEmpty(flightToInput.getText())
                        && !TextUtils.isEmpty(flightPriceInput.getText())) {
                    t.addFlight(
                            flightFromInput.getText().toString(),
                            flightToInput.getText().toString(),
                            Double.parseDouble(flightPriceInput.getText().toString()),
                            airportInput.getText().toString()
                    );
                } else {
                    Toast.makeText(getApplicationContext(), "Missing Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        addStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(stayNameInput.getText())
                        && !TextUtils.isEmpty(stayPriceInput.getText())
                        && !TextUtils.isEmpty(stayFromInput.getText())
                        && !TextUtils.isEmpty(stayToInput.getText())
                        && !TextUtils.isEmpty(stayCountyInput.getText())
                        && !TextUtils.isEmpty(stayCityInput.getText())
                        && !TextUtils.isEmpty(stayStreetInput.getText())
                        && !TextUtils.isEmpty(stayStreetNumberInput.getText())
                ) {
                    t.addStay(
                            stayNameInput.getText().toString(),
                            stayFromInput.getText().toString(),
                            stayToInput.getText().toString(),
                            stayCountyInput.getText().toString(),
                            stayCityInput.getText().toString(),
                            stayStreetInput.getText().toString(),
                            Integer.parseInt(stayStreetNumberInput.getText().toString()),
                            Double.parseDouble(stayPriceInput.getText().toString())
                    );
                    System.out.println("dodano");
                } else {
                    Toast.makeText(getApplicationContext(), "Missing Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        addTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t.airport == null) {
                    Toast.makeText(getApplicationContext(), "Missing Flight", Toast.LENGTH_LONG).show();
                }
                else if (t.stays.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Missing Stay", Toast.LENGTH_LONG).show();
                } else {
                    String dataString = HelperFunctions.makeStringFromClass(t);
                    System.out.println(HelperFunctions.makeClassFromString(dataString).flightPrice);
                    Toast.makeText(getApplicationContext(), "Added Travel " + t.stays.get(0).country, Toast.LENGTH_LONG).show();

                    myDB = new DBHelper(AddTravelActivity.this);
                    myDB.addTravel(t.stays.get(0).country, dataString);
                    travel_id = new ArrayList<>();
                    travel_name = new ArrayList<>();
                    travel_info = new ArrayList<>();

                    storeDataInArrays();


                    System.out.println("To je travel info: "+ HelperFunctions.makeClassFromString(travel_info.get(0)).flightPrice);
                    startActivity(new Intent(AddTravelActivity.this, MainActivity.class));
                }
            }
        });
    }

    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                travel_id.add(cursor.getString(0));
                travel_name.add(cursor.getString(1));
                travel_info.add(cursor.getString(2));
            }

        }
    }
}