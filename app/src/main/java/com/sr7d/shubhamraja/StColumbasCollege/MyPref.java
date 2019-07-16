package com.sr7d.shubhamraja.StColumbasCollege;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Shubham Raja on 12/28/2016.
 */

public class MyPref extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
