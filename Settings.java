package com.hybrid.tech.revisionapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Settings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
