package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.Place;

/**
 * Created by denis on 11/26/16.
 */

public interface PlaceService {
    @GET("/Places/{id}")
    Place getUserInfo(@Path("id") int id);
}
