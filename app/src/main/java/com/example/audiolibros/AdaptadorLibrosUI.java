package com.example.audiolibros;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by AMARTIN on 20/12/2016.
 */

public class AdaptadorLibrosUI extends FirebaseRecyclerAdapter<Libro,AdaptadorLibrosUI.ViewHolder> {
    //Crea Layouts a partir del XML
    //protected Vector<Libro> vectorLibros;
    protected DatabaseReference booksReference;
    private LayoutInflater inflador;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    //Vector con libros a visualizar
    private Context contexto;
    private ClickAction clickAction = new EmptyClickAction();

    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    public AdaptadorLibrosUI(Context contexto, DatabaseReference reference) {
        super(Libro.class, R.layout.elemento_selector, AdaptadorLibrosUI.ViewHolder.class, reference);
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.vectorLibros = vectorLibros;
        this.booksReference = reference;
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
        //v.setOnClickListener(onClickListener);
        v.setOnLongClickListener(onLongClickListener);
        return new ViewHolder(v);
    } // Usando como base el ViewHolder y lo personalizamos

    @Override
    public void populateViewHolder(final ViewHolder holder, final Libro libro,  final int posicion) {
        //final Libro libro = vectorLibros.elementAt(posicion);

        //holder.portada.setImageResource(libro.recursoImagen);
        /*Aplicacion aplicacion = (Aplicacion) contexto.getApplicationContext();
        aplicacion.getLectorImagenes().get(libro.urlImagen, new ImageLoader.ImageListener() {*/
        VolleySingleton.getInstance(contexto).getLectorImagenes().get(libro.getUrlImagen(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                //Bitmap bitmap = response.getBitmap();
                //holder.portada.setImageBitmap(bitmap);
                Bitmap bitmap = response.getBitmap();
                if (bitmap != null) {
                    holder.portada.setImageBitmap(bitmap);
                    /*Palette palette = Palette.from(bitmap).generate();
                    holder.itemView.setBackgroundColor(palette.getLightMutedColor(0));
                    holder.titulo.setBackgroundColor(palette.getLightVibrantColor(0));
                    holder.portada.invalidate();*/
                    holder.portada.invalidate();
                    if (libro.colorApagado == -1) {
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette palette) {
                                libro.colorApagado = palette.getLightMutedColor(0);
                                libro.colorVibrante = palette.getLightVibrantColor(0);
                                holder.itemView.setBackgroundColor(palette.getLightMutedColor(0));
                                holder.titulo.setBackgroundColor(palette.getLightVibrantColor(0));
                                holder.portada.invalidate();
                            }
                        });
                    } else {
                        holder.itemView.setBackgroundColor(libro.colorApagado);
                        holder.titulo.setBackgroundColor(libro.colorVibrante);
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                holder.portada.setImageResource(R.drawable.books);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //comentado para que compile sin errores
                // clickAction.execute(posicion);
            }
        });
        holder.titulo.setText(libro.getTitulo());
        holder.itemView.setScaleX(1);
        holder.itemView.setScaleY(1);
    }

    /*@Override
    public int getItemCount() {
        return vectorLibros.size();
    }*/
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