package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.Day;
import ru.osll.goodtravel.models.Service;

/**
 * Created by denis on 11/26/16.
 */

public interface DayService {
    @GET("/Days/{id}")
    Day getDay(@Path("id") int id);
}
