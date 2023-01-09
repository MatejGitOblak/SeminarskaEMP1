package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class TravelDetailsActivity extends AppCompatActivity {

    EditText airport, flightPrice, flightTo, flightBack,
            name, stayPrice, stayTo, stayFrom, country, city, street, streetNum;

    String airportSt, flightPriceSt, flightToSt, flightBackSt,
            nameSt, stayPriceSt, stayToSt, stayFromSt, countrySt, citySt, streetSt, streetNumSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        airport = findViewById(R.id.textInputAirport2);
        flightPrice = findViewById(R.id.textInputFlightPrice2);
        flightTo = findViewById(R.id.textInputFlightTo2);
        flightBack = findViewById(R.id.textInputFlightFrom2);
        name = findViewById(R.id.textInputStayName2);
        stayPrice = findViewById(R.id.textInputStayPrice2);
        stayTo = findViewById(R.id.textInputStayTo2);
        stayFrom = findViewById(R.id.textInputStayFrom2);
        country = findViewById(R.id.textInputStayCountry2);
        city = findViewById(R.id.textInputStayCity2);
        street = findViewById(R.id.textInputStayStreet2);
        streetNum = findViewById(R.id.textInputStayStreetNumber2);

        getAndSetIntentData();
    }
    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("airport") && getIntent().hasExtra("flightPrice") &&
                getIntent().hasExtra("flightTo") && getIntent().hasExtra("flightBack") &&
                getIntent().hasExtra("name") && getIntent().hasExtra("stayPrice") &&
                getIntent().hasExtra("stayTo") && getIntent().hasExtra("stayFrom") &&
                getIntent().hasExtra("country") && getIntent().hasExtra("city") &&
                getIntent().hasExtra("street") && getIntent().hasExtra("streetNum"))
        {
            //GET DATA FROM INTENT
            airportSt = getIntent().getStringExtra("airport");
            flightPriceSt = getIntent().getStringExtra("flightPrice");
            flightToSt = getIntent().getStringExtra("flightTo");
            flightBackSt = getIntent().getStringExtra("flightBack");

            nameSt = getIntent().getStringExtra("name");
            stayPriceSt = getIntent().getStringExtra("stayPrice");
            stayToSt = getIntent().getStringExtra("stayTo");
            stayFromSt = getIntent().getStringExtra("stayFrom");
            countrySt = getIntent().getStringExtra("country");
            citySt = getIntent().getStringExtra("city");
            streetSt = getIntent().getStringExtra("street");
            streetNumSt = getIntent().getStringExtra("streetNum");

            //SET INTENT DATA
            airport.setText(airportSt);
            flightPrice.setText(flightPriceSt);
            //Tlele je narjen en switch-ero da zamenja ko sm narobe settau pa nism najdu pa popravu,
            // ampak sou naprej da nardim cim vec
            flightTo.setText(flightBackSt);
            flightBack.setText(flightToSt);

            name.setText(nameSt);
            stayPrice.setText(stayPriceSt);
            stayTo.setText(stayToSt);
            stayFrom.setText(stayFromSt);
            country.setText(countrySt);
            city.setText(citySt);
            street.setText(streetSt);
            streetNum.setText(streetNumSt);
        }
        else
        {
            Toast.makeText(this, "Travel: No data.", Toast.LENGTH_SHORT).show();
        }
    }
}