package ru.osll.goodtravel.models;

import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Place extends RealmObject {
    String name;
    String Description;
    Address address;
    String image;
    String type;
}
