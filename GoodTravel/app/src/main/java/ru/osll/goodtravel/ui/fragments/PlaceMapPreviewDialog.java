package ru.osll.goodtravel.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Place;

/**
 * Created by artem96 on 06.02.17.
 */
public class PlaceMapPreviewDialog extends DialogFragment {

    //@TODO replace with single Place object, when standard storage will be available;
    public static final String PLACE_IMAGE_KEY = "placeImage";
    public static final String PLACE_TITLE_KEY = "placeTitle";
    public static final String PLACE_DESCRIPTION_KEY = "placeDescription";
    public static final String PLACE_PRICE_KEY = "placePrice";
    public static final String PLACE_CATEGORY_KEY = "placeCategory";

    public PlaceMapPreviewDialog() {
        super();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.map_place_preview, null));

        return builder.create();
    }



    public static PlaceMapPreviewDialog createDialog(Place place) {
        PlaceMapPreviewDialog dialog = new PlaceMapPreviewDialog();

        Bundle extra = new Bundle();

        extra.putSerializable(PLACE_CATEGORY_KEY, place.getCategory().getName());
        extra.putSerializable(PLACE_DESCRIPTION_KEY, place.getDescription());
        extra.putSerializable(PLACE_IMAGE_KEY, place.getSrcToImg());
        extra.putSerializable(PLACE_PRICE_KEY, place.getPrice());
        extra.putSerializable(PLACE_TITLE_KEY, place.getName());

        dialog.setArguments(extra);

        return dialog;
    }
}
