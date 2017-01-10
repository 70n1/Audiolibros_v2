package com.example.audiolibros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by el70n on 10/01/2017.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
