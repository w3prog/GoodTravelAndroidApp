package ru.osll.goodtravel.models;

import io.realm.RealmObject;

/**
 * Created by denis on 11/26/16.
 */

public class CategoryOfService extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
