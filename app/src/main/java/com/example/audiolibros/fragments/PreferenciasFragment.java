package com.example.audiolibros.fragments;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.audiolibros.R;

/**
 * Created by el70n on 26/12/2016.
 */

public class PreferenciasFragment extends PreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
