package com.example.audiolibros;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by AMARTIN on 01/02/2017.
 */

public class MainController {
    LibroStorage libroStorage;

    public MainController(LibroStorage libroStorage) {
        this.libroStorage = libroStorage;
    }

    public void saveLastBook(int id) {
        libroStorage.saveLastBook(id);
    }
}
