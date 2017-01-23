package ru.osll.goodtravel.ui.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.RouteResponse;
import ru.osll.goodtravel.rest.GoogleRouteService;
import ru.osll.goodtravel.utils.DBHelper;

public class ContentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback,
        Callback<RouteResponse> {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    //for google maps
    Retrofit retrofitMaps;
    GoogleRouteService routeService;

    // width of route line
    private static final float ROUTE_PATH_WIDTH = 4f;

    // color of route
    private static final int ROUTE_PATH_COLOR = R.color.vk_light_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapFragment = new SupportMapFragment();
        setFragment(mapFragment);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DBHelper.initRealm(this);

        // init google service

        retrofitMaps = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        routeService = retrofitMaps.create(GoogleRouteService.class);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFrame,fragment)
                .addToBackStack(null)
                .commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.main_path) {
            setFragment(mapFragment);
        } else if (id == R.id.menu_easy_make_route_button) {
            // start easy route make wizard
            startRouteMaker();
        } else if (id == R.id.path_list) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.signin) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Toast.makeText(this,"Working",Toast.LENGTH_LONG);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng SaintPeterburgLng = new LatLng(60, 30);
        mMap.addMarker(new MarkerOptions().position(SaintPeterburgLng).title("Маркер в Санкт-Петербурге"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SaintPeterburgLng));

        // Draw a route from obshaga to univer
        LatLng obshagaLeng = new LatLng(59.991078, 30.318714);
        LatLng univerLeng = new LatLng(59.972280, 30.322924);

        callRouteService(obshagaLeng, univerLeng);
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

    private void startRouteMaker() {

        Intent i = new Intent(this, RouteMakerActivity.class);
        startActivity(i);
    }

    @Override
    public void onFailure(Call<RouteResponse> response, Throwable t) {
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ContentActivity: ", "Cannot access google route service");
    }

    @Override
    public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
        drawRouteOnMap(response.body());
    }
}
