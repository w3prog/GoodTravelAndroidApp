package ru.osll.goodtravel.utils;

import android.util.Log;

import java.util.ArrayList;

import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.Place;

import static android.content.ContentValues.TAG;

/**
 * Created by denis on 28.01.17.
 */

public final class ModelUtils {
    public static int costPrice(ArrayList<Day> days) {
        int answer = 0;
        if (days!=null)
            for (Day a :days) {
                ArrayList<Place> places = a.getPlaces();
                if(places!=null)
                    for(Place p:places){
                        answer+=p.getPrice();
                    }
                else{
                    Log.d(TAG, "Place list is null.");
                }
            }
        else
            Log.d(TAG, "Day list is null.");
        return answer;
    }

}
