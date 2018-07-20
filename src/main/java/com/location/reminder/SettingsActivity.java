package com.location.reminder;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");
        Switch snotis = (Switch) findViewById(R.id.suggestion_notification);
        if (sp.getBoolean("snotis", true)) {

            snotis.setChecked(true);
        } else {
            snotis.setChecked(false);

        }

        Switch nnotis = (Switch) findViewById(R.id.nearby_notification);
        if (sp.getBoolean("nnotis", true)) {

            nnotis.setChecked(true);
        } else {
            nnotis.setChecked(false);

        }

    }

    public void onnchange(View view) {
        boolean on = ((Switch) view).isChecked();
        SharedPreferences.Editor editor = sp.edit();

        if (on) {
            editor.putBoolean("nnotis", true);
        } else {
            editor.putBoolean("nnotis", false);
        }
        editor.commit();

    }

    public void onchange(View view) {
        boolean on = ((Switch) view).isChecked();
        SharedPreferences.Editor editor = sp.edit();

        if (on) {
            editor.putBoolean("snotis", true);
        } else {
            editor.putBoolean("snotis", false);
        }
        editor.commit();

    }
}
