package ru.osll.goodtravel.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Plan extends RealmObject {
    private String name;
    private String dates;
    private int money;
    private String Place;
    private String feachures;
    private RealmList<Day> days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getFeachures() {
        return feachures;
    }

    public void setFeachures(String feachures) {
        this.feachures = feachures;
    }

    public RealmList<Day> getDays() {
        return days;
    }

    public void setDays(RealmList<Day> days) {
        this.days = days;
    }
}
