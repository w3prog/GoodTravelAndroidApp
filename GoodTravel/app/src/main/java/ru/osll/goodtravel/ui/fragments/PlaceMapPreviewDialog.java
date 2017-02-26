package ru.osll.goodtravel.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DataBase;

/**
 * Created by artem96 on 06.02.17.
 */
public class PlaceMapPreviewDialog extends DialogFragment {

    private static final String PLACE_ID_EXTRA_KEY = "placeIdKey";

    private Place place;

    public PlaceMapPreviewDialog() {

        super();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /* Get all arguments for this dialog */

        Bundle arguments = this.getArguments();

        long placeId = arguments.getLong(PLACE_ID_EXTRA_KEY);

        place = DataBase.PlaceRepository.get(placeId);

        /* Set up dialog */

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.map_place_preview, null);

        TextView title = (TextView) view.findViewById(R.id.place_preview_title);
        TextView description = (TextView) view.findViewById(R.id.place_preview_description);
        TextView price = (TextView) view.findViewById(R.id.place_preview_avg_price);
        TextView category = (TextView) view.findViewById(R.id.place_preview_type);
        TextView relationship = (TextView) view.findViewById(R.id.place_preview_relationship);
        ImageView picture = (ImageView) view.findViewById(R.id.place_preview_image);

        title.setText(place.getName());
        description.setText(place.getDescription());
        price.setText("" + place.getPrice());
        category.setText(place.getCategory().getName());
        relationship.setText(place.getTypeOfGroupName());
        //picture.setImageDrawable(this.getResources().getDrawable(place.getSrcToImg(), null));

        builder.setView(view);

        return builder.create();
    }

    public static PlaceMapPreviewDialog createDialog(long placeId) {
        PlaceMapPreviewDialog dialog = new PlaceMapPreviewDialog();

        Bundle extra = new Bundle();

        extra.putSerializable(PLACE_ID_EXTRA_KEY, placeId);

        dialog.setArguments(extra);

        return dialog;
    }
}
