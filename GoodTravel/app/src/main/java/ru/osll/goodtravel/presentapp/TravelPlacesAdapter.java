package ru.osll.goodtravel.presentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.osll.goodtravel.R;

/**
 * Created by artem96 on 09.11.16.
 */

public class TravelPlacesAdapter extends ArrayAdapter<TravelPlace> {

    private RouteMakerActivity maker;

    public TravelPlacesAdapter(Context context, int resourse, List<TravelPlace> objects,
                               RouteMakerActivity maker) {
        super(context, resourse, objects);
        this.maker = maker;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = maker.getLayoutInflater();

        TravelPlace place = getItem(position);

        View v = inflater.inflate(R.layout.maker_travel_list_item, null);

        TextView title = (TextView) v.findViewById(R.id.maker_list_event_title);
        title.setText(place.getName());

        TextView description = (TextView) v.findViewById(R.id.maker_list_event_description);
        description.setText(place.getDescription());

        ImageView picture = (ImageView) v.findViewById(R.id.maker_list_event_image);
        picture.setImageDrawable(maker.getResources().getDrawable(place.getPicture()));

        TextView bill = (TextView) v.findViewById(R.id.maker_list_event_average_bill);
        bill.setText(place.getAverageBill()+" Руб.");

        return v;
    }

}