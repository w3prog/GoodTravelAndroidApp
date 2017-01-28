package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.DAO.Place;

/**
 * Created by denis on 11/26/16.
 */

public interface ServiceService {
    @GET("/Services/{id}")
    Place getService(@Path("id") int id);
}
