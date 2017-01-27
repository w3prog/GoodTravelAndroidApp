package ru.osll.goodtravel.models;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 11/26/16.
 */

public class PlaceCategory extends RealmObject {

    @PrimaryKey
    private String name;
    private String strImg;
    private RealmList<Place> places;

    public PlaceCategory(){ }

    public PlaceCategory(String name, String strImg) {
        this.name = name;
        this.strImg = strImg;
    }

    public PlaceCategory(String name) {
        this.name = name;
    }

    public String getStrImg() {
        return strImg;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public RealmList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(RealmList<Place> places) {
        this.places = places;
    }

    public static PlaceCategory getByPrimaryKey(int id) {
        return Realm.getDefaultInstance()
                .where(PlaceCategory.class)
                .equalTo("id", id)
                .findFirst();
    }

    public static PlaceCategory getByName(String name) {
        return Realm.getDefaultInstance()
                .where(PlaceCategory.class)
                .equalTo("name", name)
                .findFirst();
    }

    public static List<PlaceCategory> getAll()
    {
        return Realm.getDefaultInstance()
                .where(PlaceCategory.class)
                .findAll();
    }
}
