package com.example.audiolibros;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by el70n on 25/12/2016.
 */

public class AdaptadorLibrosFiltro extends AdaptadorLibros implements Observer {
    private Vector<Integer> indiceFiltro; // Índice en vectorSinFiltro de
    // Cada elemento de vectorLibros
    private String busqueda = ""; // Búsqueda sobre autor o título
    private String genero = ""; // Género seleccionado
    private boolean novedad = false; // Si queremos ver solo novedades
    private boolean leido = false; // Si queremos ver solo leidos
    private int librosUltimoFiltro; //Número libros del padre en último filtro

    public AdaptadorLibrosFiltro(Context contexto, DatabaseReference reference) {
        super(contexto, reference);
        //vectorSinFiltro = vectorLibros;
        recalculaFiltro();
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda.toLowerCase();
        recalculaFiltro();
    }

    public void setGenero(String genero) {
        this.genero = genero;
        recalculaFiltro();
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
        recalculaFiltro();
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        recalculaFiltro();
    }

    public void recalculaFiltro() {
        //vectorLibros = new Vector<Libro>();
        indiceFiltro = new Vector<Integer>();
        librosUltimoFiltro = super.getItemCount();
        for (int i = 0; i < librosUltimoFiltro; i++) {
            Libro libro = super.getItem(i);
            if ((libro.getTitulo().toLowerCase().contains(busqueda) || libro.getAutor().toLowerCase().contains(busqueda))
                    && (libro.getGenero().startsWith(genero))
                    && (!novedad || (novedad && libro.getNovedad()))
                    //&& (!leido || (leido && libro.leido))
                    ) {
                //vectorLibros.add(libro);
                indiceFiltro.add(i);
            }
        }
    }

    public Libro getItem(int posicion) {
        if (librosUltimoFiltro != super.getItemCount()) {
            recalculaFiltro();
        }
        return super.getItem(indiceFiltro.elementAt(posicion));
    }

    public int getItemCount() {
        if (librosUltimoFiltro != super.getItemCount()) {
            recalculaFiltro();
        }
        return indiceFiltro.size();
    }


    public long getItemId(int posicion) {
        return indiceFiltro.elementAt(posicion);
    }

    public Libro getItemById(int id) { return super.getItem(id); }

    public void borrar(int posicion) {
        //vectorSinFiltro.remove((int) getItemId(posicion));
        DatabaseReference referencia=getRef(indiceFiltro.elementAt(posicion)); referencia.removeValue();
        recalculaFiltro();
    }

    public void insertar(Libro libro) {
        //vectorSinFiltro.add(0, libro);
        booksReference.push().setValue(libro);
        recalculaFiltro();
    }

    @Override
    public void update(Observable observable, Object data) {
        setBusqueda((String) data);
        notifyDataSetChanged();
    }
}