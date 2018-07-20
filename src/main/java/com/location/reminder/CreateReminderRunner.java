package com.location.reminder;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.location.reminder.com.location.reminder.restcalls.ReminderService;
import com.location.reminder.model.ReminderModel;

import org.json.JSONArray;

import java.util.ArrayList;

public class CreateReminderRunner extends AsyncTask<Void, Void, Void> {

    private ReminderModel reminderModel;


    JSONArray jsonArray;
    ProgressDialog progressdialog;
    Activity activity;
    ArrayList<String> keywords;

    public CreateReminderRunner(ReminderModel reminderModel, Activity activity, ArrayList<String> keywords) {
        this.reminderModel = reminderModel;
        this.activity = activity;
        this.keywords = keywords;
    }

    @Override
    protected Void doInBackground(Void... params) {


        String location_type = "";
        for (String keyword : keywords) {

            if (reminderModel.getTitle().contains(keyword) || reminderModel.getReminderinfo().contains(keyword)) {
                location_type = keyword;
                break;
            }
        }
        reminderModel.setLocation_type(location_type);
        ReminderService reminderService = new ReminderService();
        jsonArray = reminderService.createReminder(reminderModel);
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        progressdialog.dismiss();

        Toast.makeText(activity, "Reminder Created", Toast.LENGTH_LONG).show();
        activity.finish();

    }

    @Override
    protected void onPreExecute() {
        progressdialog = ProgressDialog.show(activity, "",
                "Please wait...", true);
    }

}
