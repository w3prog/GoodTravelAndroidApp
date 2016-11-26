package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.Address;
import ru.osll.goodtravel.models.Service;

/**
 * Created by denis on 11/26/16.
 */

public interface ServiceService {
    @GET("/Services/{id}")
    Service getService(@Path("id") int id);
}
