package com.example.audiolibros;

import android.app.Application;

import java.util.Vector;

/**
 * Created by AMARTIN on 20/12/2016.
 */

public class Aplicacion extends Application {
    private Vector<Libro> vectorLibros;
    private AdaptadorLibrosFiltro adaptador;
    @Override
    public void onCreate() {
        vectorLibros = Libro.ejemploLibros();
        adaptador = new AdaptadorLibrosFiltro (this, vectorLibros);
    }
    public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
    }
    public Vector<Libro> getVectorLibros() {
        return vectorLibros;
    }
}
