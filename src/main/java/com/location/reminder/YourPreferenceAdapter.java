package com.location.reminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.location.reminder.com.location.reminder.restcalls.GroupsService;
import com.location.reminder.model.Groups;
import com.location.reminder.model.Preference;

import java.util.ArrayList;

public class YourPreferenceAdapter extends ArrayAdapter<Preference> {

    private Activity activity;
    int layoutResourceId;
    private LayoutInflater inflater;
    Preference b;
    ArrayList<Preference> data;
    String uid;


    public YourPreferenceAdapter(Activity activity, int layoutResourceId, ArrayList<Preference> data, String uid) {
        super(activity, layoutResourceId, data);

        this.activity = activity;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.uid = uid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = ((Activity) activity).getLayoutInflater();

        }
        convertView = inflater.inflate(R.layout.preference_row, parent, false);
        b = data.get(position);
        TextView preference = (TextView) convertView.findViewById(R.id.preference);
        preference.setText(b.getName());


        return convertView;

    }


}
