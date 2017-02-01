package com.example.audiolibros;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by AMARTIN on 01/02/2017.
 */
public class VolleySingleton {

    private static RequestQueue colaPeticiones;
    private static ImageLoader lectorImagenes;
    private static VolleySingleton ourInstance;
    private final Context context;

    public static RequestQueue getColaPeticiones() {
        return colaPeticiones;
    }

    public static void setColaPeticiones(RequestQueue colaPeticiones) {
        VolleySingleton.colaPeticiones = colaPeticiones;
    }

    public static ImageLoader getLectorImagenes() {
        return lectorImagenes;
    }

    public static void setLectorImagenes(ImageLoader lectorImagenes) {
        VolleySingleton.lectorImagenes = lectorImagenes;
    }

    public static VolleySingleton getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new VolleySingleton(context);
        }
        return ourInstance;
    }

    private VolleySingleton(Context context) {
        this.context = context;
        colaPeticiones = Volley.newRequestQueue(context);
        lectorImagenes = new ImageLoader(colaPeticiones, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
        });
    }
}
