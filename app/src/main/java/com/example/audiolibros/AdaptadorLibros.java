package com.example.audiolibros;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.Vector;

/**
 * Created by AMARTIN on 20/12/2016.
 */

public class AdaptadorLibros extends RecyclerView.Adapter<AdaptadorLibros.ViewHolder> {
    //Crea Layouts a partir del XML
    protected Vector<Libro> vectorLibros;
    private LayoutInflater inflador;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    //Vector con libros a visualizar
    private Context contexto;

    public AdaptadorLibros(Context contexto, Vector<Libro> vectorLibros) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorLibros = vectorLibros;
        this.contexto = contexto;
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnItemLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View v = inflador.inflate(R.layout.elemento_selector, null);
        v.setOnClickListener(onClickListener);
        v.setOnLongClickListener(onLongClickListener);
        return new ViewHolder(v);
    } // Usando como base el ViewHolder y lo personalizamos

    @Override
    public void onBindViewHolder(final ViewHolder holder, int posicion) {
        final Libro libro = vectorLibros.elementAt(posicion);
        //holder.portada.setImageResource(libro.recursoImagen);
        Aplicacion aplicacion = (Aplicacion) contexto.getApplicationContext();
        aplicacion.getLectorImagenes().get(libro.urlImagen, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap bitmap = response.getBitmap();
                holder.portada.setImageBitmap(bitmap);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                holder.portada.setImageResource(R.drawable.books);
            }
        });

        holder.titulo.setText(libro.titulo);
        holder.itemView.setScaleX(1);
        holder.itemView.setScaleY(1);
    }

    @Override
    public int getItemCount() {
        return vectorLibros.size();
    }
// Indicamos el número de elementos de la lista

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView portada;
        public TextView titulo;

        public ViewHolder(View itemView) {
            super(itemView);
            portada = (ImageView) itemView.findViewById(R.id.portada);
            portada.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
        }
    } // Creamos el ViewHolder con las vista de un elemento sin personalizar
}