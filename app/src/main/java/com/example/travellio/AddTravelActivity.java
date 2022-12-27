package com.example.travellio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

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

    public final String flightTo, flightBack;
    public final double flightPrice;


    Travel(String flightTo, String flightBack, double flightPrice) {
        this.flightTo = flightTo;
        this.flightBack = flightBack;
        this.flightPrice = flightPrice;
    }

    public void addStay(String name, String from, String to, String country, String city, String street, int streetNumber, double price) {
        this.stays.add(new Stay(name, from, to, country, city, street, streetNumber, price));
    }
}


public class AddTravelActivity extends AppCompatActivity {

    public String makeStringFromClass(Travel modeldata) {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(modeldata);
            byte[] employeeAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
            int[] arr = new int[employeeAsBytes.length];

            for(int i = 0; i < employeeAsBytes.length; i++) {
                arr[i] = (int)employeeAsBytes[i];
            }

            String strOfInts = Arrays.toString(arr).replaceAll("\\[|\\]|,", "");
            strOfInts = strOfInts.trim();
            return strOfInts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Travel makeClassFromString(String data) {
        String[] words = data.split(" ");

        byte[] arr2 = new byte[words.length];
        for(int i = 0; i < words.length; i++) {
            arr2[i] = Byte.parseByte(words[i]);
        }
        try {
            ByteArrayInputStream baip = new ByteArrayInputStream(arr2);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Travel dataobj = (Travel ) ois.readObject();
            return dataobj ;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        Travel t = new Travel("21.2.2022", "25.2.2022", 443.2);

        String dataString = makeStringFromClass(t);
        Travel t1 = makeClassFromString(dataString);

        System.out.println(t1.flightPrice);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}