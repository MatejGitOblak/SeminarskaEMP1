package com.example.travellio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class HelperFunctions {

    public static String makeStringFromClass(Travel modeldata) {
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

    public static Travel makeClassFromString(String data) {
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

}
