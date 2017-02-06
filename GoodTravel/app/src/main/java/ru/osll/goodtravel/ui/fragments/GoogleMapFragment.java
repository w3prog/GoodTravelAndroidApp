package ru.osll.goodtravel.ui.fragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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
public class GoogleMapFragment extends SupportMapFragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    //for google maps
    Retrofit retrofitMaps;
    GoogleRouteService routeService;

    // width of route line
    private static final float ROUTE_PATH_WIDTH = 4f;

    // color of route
    private static final int ROUTE_PATH_COLOR = R.color.vk_light_color;

    /**
     * Last route obtained from google route service
     */
    private RouteResponse lastRoute;

    // temp fake data array
    private ArrayList<Place> places;

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
        places = new ArrayList<>();
        places.add(new Place("Русский музей",340,place,"59.99107,30.31871",c1));
        places.add(new Place("Эрмитаж",400,place,"59.99207,30.31843",c1));
        places.add(new Place("Музей артиллерии",1200,place,"59.99253,30.31864",c1));
        places.add(new Place("Музей радиосвязи",600,place,"59.99264,30.31854",c1));
        Place placex = new Place("Кунскамера",700,place,"59.99254,30.31864",c1, TypeOfGroupEnum.NO_FAMILY);
        placex.setDescription("Place, you want to visit.");

        places.add(placex);
        places.add(new Place("Экскурсия по Санкт-Петербургу",2000,place,"59.99256,30.31854",c2));
        places.add(new Place("Водная экскурсия по Санкт-Петербургу",2200,place,"59.99254,30.31854",c2));
        places.add(new Place("Экскурсия по Петергофу",450,place,"59.99243,30.31854",c2));
        places.add(new Place("Экскурсия по городу Пушкину",700,place,"59.99254,30.31864",c2));
        places.add(new Place("Экскурсия по Кромштату",800,place,"59.99234,30.31854",c2));

        Day testDay = new Day();
        testDay.setPlaces(places);

        showDayInMap(testDay);

        // redefine onMarkerClickListener for
        // our use
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                DialogFragment dialog = PlaceMapPreviewDialog.createDialog(findPlaceByName(marker.getTitle()));

                dialog.show(getActivity().getFragmentManager(), marker.getTitle());

                return false;
            }
        });
    }

    public void showDayInMap(@NonNull Day day){
        ArrayList<Place> places = day.getPlaces();

        places.get(1).getCoordinate();
        for (int i = 0; i < places.size() - 1; i++) {
            callRouteService(places.get(i), places.get(i+1));
        }
    }

    /**
     * Call a google route service.
     *
     * @param position - start place
     * @param destination - finish place
     */
    public void callRouteService(Place position, Place destination) {

        String pos = position.getCoordinate();
        String dest = destination.getCoordinate();

        Call<RouteResponse> rawCall = routeService.getRoute(pos, dest, true, "ru");

        RouteServiceCallback callback = new RouteServiceCallback(position, destination);

        rawCall.enqueue(callback);

    }

    /**
     * Draw route on the map, based on google route service
     * response. Call callRouteService method for response obj.
     *
     * @param routeInfo - information from Google about route
     * @param first - first place where we start
     * @param second - place, where we wanna go
     */
    private void drawRouteOnMap(RouteResponse routeInfo, Place first, Place second) {


        List<LatLng> route = PolyUtil.decode(routeInfo.getPoints());

        PolylineOptions line = new PolylineOptions();
        line.width(ROUTE_PATH_WIDTH).color(ROUTE_PATH_COLOR);
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

        for (int i = 0; i < route.size(); i++) {

            if (i == 0 || i == route.size()-1) {

                String title, description;

                if (i == 0) {
                    title = first.getName();
                    description = second.getDescription();
                } else {
                    title = second.getName();
                    description = second.getDescription();
                }

                MarkerOptions nextMarkerOptions = new MarkerOptions()
                        .position(route.get(i))
                        .title(title)
                        .snippet(description);
                mMap.addMarker(nextMarkerOptions);
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

    /**
     * Return place according to given
     * coordinates
     *
     * @param coords - coords of place
     * @return place
     */
    private Place getPlaceByCoords(LatLng coords) {
        for (Place place : places) {
            String[] coord = place.getCoordinate().split(",");

            double lat,lng;

            lat = Double.parseDouble(coord[0]);
            lng = Double.parseDouble(coord[1]);

            if (coords.equals(new LatLng(lat, lng))) {

                return place;
            }
        }

        return null;
    }

    /**
     * Find place by given name
     *
     * need it for OnMarkerClick function
     */
    private Place findPlaceByName(String name) {
        for (Place place : places) {
            if (place.getName().equals(name)) {
                return place;
            }
        }

        return null;
    }


    private class RouteServiceCallback implements Callback<RouteResponse> {

        private Place firstPlace;
        private Place secondPlace;

        RouteServiceCallback(Place firstPlace, Place secondPlace) {
            this.firstPlace = firstPlace;
            this.secondPlace = secondPlace;
        }

        @Override
        public void onFailure(Call<RouteResponse> response, Throwable t) {
            Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.e("GoogleMapFragment: ", "Cannot access google route service");
        }

        @Override
        public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
            drawRouteOnMap(response.body(), firstPlace, secondPlace);
        }

        public Place getFirstPlace() {
            return firstPlace;
        }

        public Place getSecondPlace() {
            return secondPlace;
        }
    }


}
