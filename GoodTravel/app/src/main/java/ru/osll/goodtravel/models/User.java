package ru.osll.goodtravel.models;

import io.realm.RealmObject;

/**
 * Created by denis on 11/26/16.
 */

public class User extends RealmObject {

    //// TODO: 11/26/16 что нам нужно знать о пользователе на телефоне?

    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
