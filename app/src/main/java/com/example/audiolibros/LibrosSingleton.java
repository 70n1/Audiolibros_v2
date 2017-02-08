package com.example.audiolibros;

import android.content.Context;

import java.util.Vector;

/**
 * Created by AMARTIN on 01/02/2017.
 */
public class LibrosSingleton {

    private static AdaptadorLibrosFiltro adaptador;
    //private static Vector<Libro> vectorLibros;
    private final Context context;

    private static LibrosSingleton ourInstance;

    public static LibrosSingleton getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new LibrosSingleton(context);
        }
        return ourInstance;
    }

    private LibrosSingleton(Context context) {
        this.context = context;
        //vectorLibros = Libro.ejemploLibros();
        adaptador = new AdaptadorLibrosFiltro(context, Aplicacion.booksReference);
    }


    public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
    }

    /*public Vector<Libro> getVectorLibros() {
        return vectorLibros;
    }*/
}
