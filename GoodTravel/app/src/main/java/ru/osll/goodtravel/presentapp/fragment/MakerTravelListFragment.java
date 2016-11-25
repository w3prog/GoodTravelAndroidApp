package ru.osll.goodtravel.presentapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.presentapp.RouteMakerActivity;
import ru.osll.goodtravel.presentapp.RouteMakerInfoBundle;
import ru.osll.goodtravel.presentapp.TravelPlace;

import java.util.List;

/**
 * Created by artem96 on 13.10.16.
 */

public class MakerTravelListFragment extends Fragment {

    RouteMakerInfoBundle routeInfo;
    RouteMakerActivity maker;

    SimpleCursorAdapter cursorAdapter;
    RecyclerView recyclerView;

    public MakerTravelListFragment(RouteMakerActivity maker, RouteMakerInfoBundle routeInfo) {
        this.routeInfo = routeInfo;
        this.maker = maker;
    }

    public static MakerTravelListFragment createInstance(
            RouteMakerActivity maker, RouteMakerInfoBundle routeInfo) {
        MakerTravelListFragment fragment = new MakerTravelListFragment(maker, routeInfo);

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = (View) inflater.inflate(R.layout.maker_travel_list_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.route_maker_list);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(new TravelListAdapter(maker.getFakePlaces()));

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        Log.e("LIST FRAGMENT","Places: "+maker.getFakePlaces().size());


        return v;
    }

    private class TravelListItemHolder extends RecyclerView.ViewHolder {

        private ImageView placePicture;
        private TextView placeTitle;
        private TextView placeDescription;
        private TextView averageBill;

        TravelListItemHolder(View view) {
            super(view);

            placePicture = (ImageView) view.findViewById(R.id.maker_list_event_image);
            placeTitle = (TextView) view.findViewById(R.id.maker_list_event_title);
            placeDescription = (TextView) view.findViewById(R.id.maker_list_event_description);
            averageBill = (TextView) view.findViewById(R.id.maker_list_event_average_bill);
        }
    }

    private class TravelListAdapter extends
            RecyclerView.Adapter<TravelListItemHolder> {

        private List<TravelPlace> places;

        TravelListAdapter(List<TravelPlace> places) {

            if (places == null) {
                throw new IllegalArgumentException("places must not be null");
            }

            this.places = places;
        }

        @Override
        public TravelListItemHolder onCreateViewHolder(ViewGroup viewHolder, int viewType) {

            View view = LayoutInflater.from(viewHolder.getContext()).
                    inflate(R.layout.maker_travel_list_item, viewHolder, false);

            return new TravelListItemHolder(view);
        }

        @Override
        public void onBindViewHolder(TravelListItemHolder itemHolder, int position) {

            TravelPlace model = places.get(position);

            itemHolder.placeTitle.setText(model.getName());
            itemHolder.placeDescription.setText(model.getDescription());
            itemHolder.averageBill.setText(model.getAverageBill()+" Руб");
            itemHolder.placePicture.setImageDrawable(getActivity().getResources().
                    getDrawable(model.getPicture()));

        }

        @Override
        public int getItemCount() {
            return places.size();
        }

    }

}
