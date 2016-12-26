package com.example.audiolibros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.audiolibros.fragments.PreferenciasFragment;

/**
 * Created by el70n on 26/12/2016.
 */

public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenciasFragment()).commit();
    }
}
