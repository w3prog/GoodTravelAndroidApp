package ru.osll.goodtravel.models;

import java.util.List;

/**
 * Model for JSON response from Google Maps API
 * server.
 *
 * Created by artem96 on 17.12.16.
 */

public class RouteResponse {

    public List<Route> routes;

    public String getPoints() {
        return this.routes.get(0).overview_polyline.points;
    }

    class Route {
        OverviewPolyline overview_polyline;
    }

    class OverviewPolyline {
        String points;
    }

}
