package ru.osll.goodtravel.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.osll.goodtravel.models.Day;
import ru.osll.goodtravel.models.Plan;

/**
 * Created by denis on 11/26/16.
 */

public interface PlanService {
    @GET("/Plans/{id}")
    Plan getPlan(@Path("id") int id);
}
