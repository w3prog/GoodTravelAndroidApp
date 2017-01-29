package ru.osll.goodtravel.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem96 on 13.10.16.
 */

public class MakerTravelListFragment extends BaseFragment {

    RouteMakerInfoBundle routeInfo;
    RouteMakerActivity maker;

    RecyclerView recyclerView;

    public static List<Place> placeList;

    public static MakerTravelListFragment createInstance(
            RouteMakerActivity maker, RouteMakerInfoBundle routeInfo) {
        MakerTravelListFragment fragment = new MakerTravelListFragment();
        fragment.routeInfo = routeInfo;
        fragment.maker = maker;
        placeList = new ArrayList<>();

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = (View) inflater.inflate(R.layout.maker_travel_list_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.route_maker_list);
        recyclerView.setNestedScrollingEnabled(false);

        return v;
    }

    @Override
    public void request()
    {
        placeList.clear();


        final List<Place> placeArrayList = new ArrayList<>();

        for(int i = 0; i < RouteMakerActivity.placeCategoryList.size(); i++)
        {
            // TODO: 29.01.17 реализовать
//            placeArrayList.addAll(Place.getServices(
//                    RouteMakerActivity.placeCategoryList.get(i), RouteMakerActivity.progress));
        }

        TravelListAdapter adapter = new TravelListAdapter(new ArrayList<>(placeArrayList));

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Place selectedPlace = placeArrayList.get(i);

                if(placeList.contains(selectedPlace))
                {
                    placeList.remove(selectedPlace);
                }
                else
                {
                    placeList.add(selectedPlace);
                }
            }
        });

        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    private class TravelListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView placePicture;
        private TextView placeTitle;
        private TextView placeDescription;
        private TextView averageBill;
        private AdapterView.OnItemClickListener onItemClickListener;

        TravelListItemHolder(View view) {
            super(view);

            placePicture = (ImageView) view.findViewById(R.id.maker_list_event_image);
            placeTitle = (TextView) view.findViewById(R.id.maker_list_event_title);
            placeDescription = (TextView) view.findViewById(R.id.maker_list_event_description);
            averageBill = (TextView) view.findViewById(R.id.maker_list_event_average_bill);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            view = view.findViewById(R.id.tickImageView);

            if(view.getVisibility() == View.GONE)
            {
                view.setVisibility(View.VISIBLE);
            }
            else
            {
                view.setVisibility(View.GONE);
            }

            if(onItemClickListener != null)
                onItemClickListener.onItemClick(null, view, getAdapterPosition(), getAdapterPosition());
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
        {
            this.onItemClickListener = onItemClickListener;
        }
    }

    private class TravelListAdapter extends RecyclerView.Adapter<TravelListItemHolder> {

        private List<Place> places;
        private AdapterView.OnItemClickListener onItemClickListener;

        TravelListAdapter(List<Place> places) {

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

            Place model = places.get(position);

            itemHolder.placeTitle.setText(model.getName());
            itemHolder.placeDescription.setText(model.getName());
            itemHolder.averageBill.setText(model.getPrice()+" Руб");
            itemHolder.setOnItemClickListener(onItemClickListener);
            if(model.getSrcToImg() != null && !model.getSrcToImg().isEmpty())
            {
                Picasso
                        .with(getContext())
                        .load(model.getSrcToImg())
                        .into(itemHolder.placePicture);
            }
            else
            {
                itemHolder.placePicture.setImageResource(android.R.drawable.sym_def_app_icon);
            }
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
        {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public int getItemCount() {
            return places.size();
        }
    }
}
