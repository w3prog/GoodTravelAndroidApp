package ru.osll.goodtravel.ui.fragments;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.Day;
import ru.osll.goodtravel.models.responses.RouteResponse;
import ru.osll.goodtravel.rest.GoogleRouteService;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleMapFragment extends SupportMapFragment implements OnMapReadyCallback,
        Callback<RouteResponse> {
    private GoogleMap mMap;

    //for google maps
    Retrofit retrofitMaps;
    GoogleRouteService routeService;

    // width of route line
    private static final float ROUTE_PATH_WIDTH = 4f;

    // color of route
    private static final int ROUTE_PATH_COLOR = R.color.vk_light_color;

    public GoogleMapFragment() {
        // init google service

        retrofitMaps = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        routeService = retrofitMaps.create(GoogleRouteService.class);
        this.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng SaintPeterburgLng = new LatLng(60, 30);
        mMap.addMarker(new MarkerOptions().position(SaintPeterburgLng)
                .title("Маркер в Санкт-Петербурге"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SaintPeterburgLng));

        // Draw a route from obshaga to univer
        LatLng obshagaLeng = new LatLng(59.991078, 30.318714);
        LatLng univerLeng = new LatLng(59.972280, 30.322924);

        callRouteService(obshagaLeng, univerLeng);
    }

    public void showDay(Day day){
        // TODO: 27.01.17 Реализовать отображения маршрута на один день
    }

    /**
     * Call a google route service.
     *
     * @param origin - first point of route
     * @param destination - second point of route
     */
    public void callRouteService(LatLng origin, LatLng destination) {

        String position = new StringBuilder()
                .append(origin.latitude)
                .append(",")
                .append(origin.longitude)
                .toString();

        String dest = new StringBuilder()
                .append(destination.latitude)
                .append(",")
                .append(destination.longitude)
                .toString();

        Call<RouteResponse> rawCall = routeService.getRoute(position, dest, true, "ru");

        rawCall.enqueue(this);

    }

    /**
     * Draw route on the map, based on google route service
     * response. Call callRouteService method for response obj.
     *
     * @param routeInfo
     */
    private void drawRouteOnMap(RouteResponse routeInfo) {


        List<LatLng> route = PolyUtil.decode(routeInfo.getPoints());

        PolylineOptions line = new PolylineOptions();
        line.width(ROUTE_PATH_WIDTH).color(ROUTE_PATH_COLOR);
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

        for (int i = 0; i < route.size(); i++) {
            if (i == 0) {
                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(route.get(i))
                        .title("Начальная точка маршрута");
                mMap.addMarker(startMarkerOptions);
            } else if (i == route.size() - 1) {
                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(route.get(i))
                        .title("Конечная точка маршрута");
                mMap.addMarker(startMarkerOptions);
            }

            line.add(route.get(i));
            latLngBuilder.include(route.get(i));
        }

        mMap.addPolyline(line);
        int size = getResources().getDisplayMetrics().widthPixels;
        LatLngBounds latLngBounds = latLngBuilder.build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25);
        mMap.moveCamera(track);
    }



    @Override
    public void onFailure(Call<RouteResponse> response, Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ContentActivity: ", "Cannot access google route service");
    }

    @Override
    public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
        drawRouteOnMap(response.body());
    }
}
