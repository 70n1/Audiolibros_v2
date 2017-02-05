package com.example.audiolibros;

/**
 * Created by el70n on 05/02/2017.
 */

public class BooksRespository {
    private final LibroStorage librosStorage;

    public BooksRespository(LibroStorage librosStorage) {
        this.librosStorage = librosStorage;
    }

    public int getLastBook() {
        return librosStorage.getLastBook();
    }
}

