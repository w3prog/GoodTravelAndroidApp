package ru.osll.goodtravel.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.Service;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.models.TravelPlace;

import java.util.List;

/**
 * Created by artem96 on 13.10.16.
 */

public class MakerTravelPackingFragment extends BaseFragment {

    private RouteMakerInfoBundle routeInfo;
    private LinearLayout packingContainer;
    private RouteMakerActivity maker;
    private RecyclerView view;

    public MakerTravelPackingFragment(RouteMakerInfoBundle routeInfo, RouteMakerActivity maker) {
        this.routeInfo = routeInfo;
        this.maker = maker;
    }

    public static MakerTravelPackingFragment createInstance(RouteMakerInfoBundle routeInfo,
                                                            RouteMakerActivity maker) {

        MakerTravelPackingFragment fragment = new MakerTravelPackingFragment(routeInfo, maker);

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.maker_travel_packing_fragment, container, false);

        TextView dayLabel = (TextView) v.findViewById(R.id.maker_packing_firstDayLabel);

        dayLabel.setText(routeInfo.getFirstDay().getDate().toString());

        view = (RecyclerView) v.findViewById(R.id.packing_list_container);


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        view.setLayoutManager(manager);

        return v;
    }

    @Override
    public void request()
    {
        view.setAdapter(new PackingListAdapter(MakerTravelListFragment.serviceList));
    }

    private class PackingListItemHolder extends RecyclerView.ViewHolder {

        TextView placeTitle;
        ImageView placePicture;

        PackingListItemHolder(View view) {
            super(view);

            placePicture = (ImageView) view.findViewById(R.id.packing_list_item_image);
            placeTitle = (TextView) view.findViewById(R.id.packing_list_item_label);
        }
    }

    private class PackingListAdapter extends
            RecyclerView.Adapter<PackingListItemHolder> {

        private List<Service> places;

        PackingListAdapter(List<Service> places) {

            if (places == null) {
                throw new IllegalArgumentException("places must not be null");
            }

            this.places = places;
        }

        @Override
        public PackingListItemHolder onCreateViewHolder(
                ViewGroup viewGroup, int viewType) {

            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.packing_list_item,
                            viewGroup, false);

            return new PackingListItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(
                PackingListItemHolder viewHolder, int position) {

            Service model = places.get(position);

            viewHolder.placeTitle.setText(model.getName());

            if(model.getSrcToImg() != null && !model.getSrcToImg().isEmpty())
            {
                Picasso
                        .with(getContext())
                        .load(model.getSrcToImg())
                        .into(viewHolder.placePicture);
            }
            else
            {
                viewHolder.placePicture.setImageResource(android.R.drawable.sym_def_app_icon);
            }

        }

        @Override
        public int getItemCount() {
            return places.size();
        }

    }



}
