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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(RealmList<Place> places) {
        this.places = places;
    }
}
