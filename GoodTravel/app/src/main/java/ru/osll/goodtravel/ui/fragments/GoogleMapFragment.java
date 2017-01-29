package ru.osll.goodtravel.ui.fragments;


import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.osll.goodtravel.R;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.RouteResponse;
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

        LatLng SaintPeterburgLng = new LatLng(60, 30);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SaintPeterburgLng));
        PlaceCategory c1 = new PlaceCategory("Музей",
                "http://moodle.presby.edu/file.php/1/library.png");
        PlaceCategory c2 = new PlaceCategory("Экскурсии по городу");
        String place = "Санкт-Петербург";
        ArrayList<Place> places = new ArrayList<>();
        places.add(new Place("Русский музей",340,place,"59.991078,30.318714",c1));
        places.add(new Place("Эрмитаж",400,place,"59.992078,30.318435",c1));
        places.add(new Place("Музей артиллерии",1200,place,"59.992534,30.318643",c1));
        places.add(new Place("Музей радиосвязи",600,place,"59.992643,30.318543",c1));
        places.add(new Place("Кунскамера",700,place,"59.992548,30.318643",c1, TypeOfGroupEnum.NO_FAMILY));
        places.add(new Place("Экскурсия по Санкт-Петербургу",2000,place,"59.992564,30.318543",c2));
        places.add(new Place("Водная экскурсия по Санкт-Петербургу",2200,place,"59.992543,30.318543",c2));
        places.add(new Place("Экскурсия по Петергофу",450,place,"59.992436,30.318543",c2));
        places.add(new Place("Экскурсия по городу Пушкину",700,place,"59.992546,30.318643",c2));
        places.add(new Place("Экскурсия по Кромштату",800,place,"59.992345,30.318543",c2));

        Day testDay = new Day();
        testDay.setPlaces(places);

        showDayInMap(testDay);
    }

    public void showDayInMap(@NonNull Day day){
        ArrayList<Place> places = day.getPlaces();

        places.get(1).getCoordinate();
        for (int i = 0; i < places.size() - 1; i++) {
            //получить координаты
            String position = places.get(i).getCoordinate();
            String dest = places.get(i+1).getCoordinate();
            callRouteService(position,dest);
        }
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
    public void callRouteService(String position, String dest) {

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
                        //// TODO: Для Артема 28.01.17 Нужно как-то передать сведения о названии места
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
        Log.e("GoogleMapFragment: ", "Cannot access google route service");
    }

    @Override
    public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
        drawRouteOnMap(response.body());
    }
}
