package com.example.audiolibros;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by AMARTIN on 09/01/2017.
 */

public class AudioLibrosWidget extends AppWidgetProvider {
    RemoteViews remoteViews;
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] widgetIds) {
        for (int widgetId: widgetIds) {
            irUltimoVisitado(context, widgetId);
        }
    }
    public void irUltimoVisitado(Context context, int widgetId) {
        SharedPreferences pref = context.getSharedPreferences(
                "com.example.audiolibros_internal", Context.MODE_PRIVATE);
        int id = pref.getInt("ultimo", -1);
        /*if ((id==-1) && ((Aplicacion) context.getApplicationContext())
                .getVectorLibros().size()>0) {*/
        if ((id==-1) && LibrosSingleton.getInstance(context)
                .getVectorLibros().size()>0) {
            id=0;
        }

        if (id >= 0) {
            /*Libro libro = ((Aplicacion) context.getApplicationContext())
                    .getVectorLibros().elementAt(id);*/
            Libro libro = LibrosSingleton.getInstance(context)
                    .getVectorLibros().elementAt(id);
            remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget);
            remoteViews.setTextViewText(R.id.textView1, libro.getTitulo());
            remoteViews.setTextViewText(R.id.textView2, libro.getAutor());

            /*Aplicacion aplicacion = (Aplicacion) context.getApplicationContext();
            aplicacion.getLectorImagenes().get(libro.urlImagen, new ImageLoader.ImageListener() {*/
            VolleySingleton.getInstance(context).getLectorImagenes().get(libro.getUrlImagen(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    //Bitmap bitmap = response.getBitmap();
                    //holder.portada.setImageBitmap(bitmap);

                    Bitmap bitmap = response.getBitmap();
                    if (bitmap != null) {
                        remoteViews.setImageViewBitmap(R.id.imagenWidget,response.getBitmap());
                    /*Palette palette = Palette.from(bitmap).generate();
                    holder.itemView.setBackgroundColor(palette.getLightMutedColor(0));
                    holder.titulo.setBackgroundColor(palette.getLightVibrantColor(0));
                    holder.portada.invalidate();*/
                    } else {
                        remoteViews.setImageViewResource(R.id.imagenWidget,R.drawable.books);
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    remoteViews.setImageViewResource(R.id.imagenWidget,R.drawable.books);
                }
            });

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("ID", id);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
            AppWidgetManager.getInstance(context).updateAppWidget(widgetId, remoteViews);
        }
    }
}
