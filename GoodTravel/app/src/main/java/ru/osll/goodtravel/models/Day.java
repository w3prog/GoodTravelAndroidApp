package ru.osll.goodtravel.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Day extends RealmObject {
    private Plan plan;
    private Date date;
    private RealmList<Place> places;
}
