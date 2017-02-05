package com.example.audiolibros;

/**
 * Created by el70n on 05/02/2017.
 */

public class SaveLastBook {
    private final LibroStorage librosStorage;

    public SaveLastBook(LibroStorage librosStorage) {
        this.librosStorage = librosStorage;
    }

    public void execute(int id) {
        librosStorage.saveLastBook(id);
    }
}
