package ru.osll.goodtravel.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.osll.goodtravel.models.RouteResponse;

/**
 * Возвращает путь, основанный на
 * передаваемых параметрах destination
 * и origin.
 *
 * Путь возвращается в виде JSON
 *
 * Created by artem96 on 17.12.16.
 */

public interface GoogleRouteService {

    @GET("/maps/api/directions/json")
    Call<RouteResponse> getRoute(
            @Query("origin") String position,
            @Query("destination") String destination,
            @Query("sensor") boolean sensor,
            @Query("language") String language);

}
