package com.example.audiolibros;

/**
 * Created by AMARTIN on 30/01/2017.
 */

public interface LibroStorage {
    boolean hasLastBook();
    String getLastBook();

    void saveLastBook(String key);
}
