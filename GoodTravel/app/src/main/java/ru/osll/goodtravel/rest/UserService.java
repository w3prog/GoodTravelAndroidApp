package ru.osll.goodtravel.rest;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.User;

/**
 * Created by denis on 11/26/16.
 */

public interface UserService {
    @GET("/users/{id}")
    User getUser(@Path("id") int id);
}
