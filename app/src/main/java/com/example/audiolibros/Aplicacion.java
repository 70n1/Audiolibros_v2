package com.example.audiolibros;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Vector;

/**
 * Created by AMARTIN on 20/12/2016.
 */

public class Aplicacion extends Application {


    private FirebaseAuth auth;

    @Override
    public void onCreate() {
        auth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }
}
