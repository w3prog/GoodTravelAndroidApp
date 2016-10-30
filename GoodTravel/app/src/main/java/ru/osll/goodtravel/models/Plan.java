package ru.osll.goodtravel.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Plan extends RealmObject {
    String name;
    String dates;
    Long money;
    String Place;
    String feachures;
    RealmList<Day> days;

}
