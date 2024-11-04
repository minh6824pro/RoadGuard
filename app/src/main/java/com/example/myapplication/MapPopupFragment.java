package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapPopupFragment extends Fragment {

    private static final String ARG_LOCATION = "location";
    private static final String ARG_DETECTED_BY = "detectedBy";
    private static final String ARG_IMAGE_RES_ID = "imageResId";
    private static final String ARG_STATUS = "status";

    public static MapPopupFragment newInstance(String location, String detectedBy, int imageResId, String statusText) {
        MapPopupFragment fragment = new MapPopupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOCATION, location);
        args.putString(ARG_DETECTED_BY, detectedBy);
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        args.putString(ARG_STATUS, statusText);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup_map, container, false);

        TextView locationText = view.findViewById(R.id.locationText);
        TextView detectedByText = view.findViewById(R.id.detectedByText);
        ImageView reportImage = view.findViewById(R.id.reportImage);
        Button statusButton = view.findViewById(R.id.statusButton);

        Bundle args = getArguments();
        if (args != null) {
            locationText.setText(args.getString(ARG_LOCATION));
            detectedByText.setText(args.getString(ARG_DETECTED_BY));
            reportImage.setImageResource(args.getInt(ARG_IMAGE_RES_ID));
            statusButton.setText(args.getString(ARG_STATUS));
            // Log để kiểm tra thông tin
            Log.d("MapPopupFragment", "Location: " + args.getString(ARG_LOCATION));
        } else {
            Log.e("MapPopupFragment", "Arguments are null");
        }

        return view;
    }

}
