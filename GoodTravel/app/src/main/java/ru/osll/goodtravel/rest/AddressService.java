package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.Address;

/**
 * Created by denis on 11/26/16.
 */

public interface AddressService {
    @GET("/address/{id}")
    Address getAddress(@Path("id") int id);
}
