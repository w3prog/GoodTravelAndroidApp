package ru.osll.goodtravel.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 11/26/16.
 */

public class User extends RealmObject {

    //// TODO: 11/26/16 что нам нужно знать о пользователе на телефоне?


    public User() {
    }

    @PrimaryKey
    private long id;


    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
