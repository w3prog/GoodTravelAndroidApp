package ru.osll.goodtravel.models;

import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Address extends RealmObject {
    private String country;
    private String are;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAre() {
        return are;
    }

    public void setAre(String are) {
        this.are = are;
    }
}
