package com.location.reminder;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.location.reminder.model.PreferenceReminder;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NearbyPlacesAdapter extends ArrayAdapter<PreferenceReminder> {


    private Activity activity;
    int layoutResourceId;
    private LayoutInflater inflater;
    PreferenceReminder b;
    ArrayList<PreferenceReminder> data;

    LatLng currentLocation;

    public NearbyPlacesAdapter(Activity activity, int layoutResourceId, ArrayList<PreferenceReminder> data, LatLng currentLocation) {
        super(activity, layoutResourceId, data);

        this.activity = activity;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.currentLocation = currentLocation;

    }

    public void openMap(String srclatitude, String srclongitude, String destlatitude, String destlongitude) {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + srclatitude + "," + srclongitude + "&daddr=" + destlatitude + "," + destlongitude + ""));
        activity.startActivity(intent);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            inflater = ((Activity) activity).getLayoutInflater();

        }
        convertView = inflater.inflate(R.layout.place_row, parent, false);
        b = data.get(position);

        ImageView thumbnail_image = (ImageView) convertView.findViewById(R.id.thumbnail_image);
        thumbnail_image.setScaleType(ImageView.ScaleType.FIT_XY);

        Picasso.with(activity).load(b.getImage()).into(thumbnail_image);


        TextView recycle_title = (TextView) convertView.findViewById(R.id.recycle_title);
        recycle_title.setText(b.getTitle());

        TextView reminder_location = (TextView) convertView.findViewById(R.id.reminder_location);
        reminder_location.setText(b.getLatitude() + " " + b.getLongitude());

        TextView reminder_info = (TextView) convertView.findViewById(R.id.reminder_info);
        reminder_info.setText(b.getPreferencetype());

        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        rating.setText("Rating : "+b.getRating());

        TextView price_level = (TextView) convertView.findViewById(R.id.price_level);
        price_level.setText("Price Level : "+b.getPrice_level());

        TextView preferencerating = (TextView) convertView.findViewById(R.id.preferencerating);
        preferencerating.setText("Preference Level : " + new DecimalFormat("#.##").format(b.getPreferencerating()));

        Button mapview = (Button) convertView.findViewById(R.id.mapview);

        mapview.setTag(b);

        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceReminder preferenceReminder = (PreferenceReminder) view.getTag();

                System.out.println(currentLocation.latitude);
                System.out.println(currentLocation.longitude);
                System.out.println(preferenceReminder.getLatitude());
                System.out.println(preferenceReminder.getLongitude());

                openMap("" + currentLocation.latitude, "" + currentLocation.longitude, preferenceReminder.getLatitude(), preferenceReminder.getLongitude());

            }
        });


        return convertView;
    }

}
