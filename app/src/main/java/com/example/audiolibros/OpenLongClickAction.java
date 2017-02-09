package com.example.audiolibros;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.audiolibros.fragments.SelectorFragment;

import java.util.Vector;

/**
 * Created by el70n on 05/02/2017.
 */

public class OpenLongClickAction implements ClickAction {
    //private final MainActivity mainActivity;
    private final SelectorFragment selectorFragment;
    private final View view;
    private AdaptadorLibrosFiltro adaptador;
    private Vector<Libro> vectorLibros;


    public OpenLongClickAction(SelectorFragment selectorFragment, View view, AdaptadorLibrosFiltro adaptador, Vector<Libro> vectorLibros) {
        this.selectorFragment = selectorFragment;
        this.view = view;
        this.adaptador = adaptador;
        this.vectorLibros = vectorLibros;
    }

    @Override
    public void execute(final String key) {

        //final int id = pos;
        AlertDialog.Builder menu = new AlertDialog.Builder(selectorFragment.getActivity());
        CharSequence[] opciones = {"Compartir", "Borrar ", "Insertar"};
        menu.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int opcion) {
                switch (opcion) {
                    case 0: //Compartir
                        Animator anim = AnimatorInflater.loadAnimator(selectorFragment.getActivity(), R.animator.agrandar);
                        anim.addListener(selectorFragment);
                        anim.setTarget(view);
                        anim.start();
                        Libro libro = adaptador.getItemByKey(key);
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, libro.getTitulo());
                        i.putExtra(Intent.EXTRA_TEXT, libro.getUrlAudio());
                        selectorFragment.getActivity().startActivity(Intent.createChooser(i, "Compartir"));
                        break;
                    case 1: //Borrar
                        Snackbar.make(view, "¿Estás seguro?", Snackbar.LENGTH_LONG).setAction("SI", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        /*
                                        //Utilizando anim
                                        Animation anim = AnimationUtils.loadAnimation(actividad, R.anim.menguar);
                                        anim.setAnimationListener(SelectorFragment.this);
                                        v.startAnimation(anim);*/

                                //unitilizando animator
                                Animator anim = AnimatorInflater.loadAnimator(selectorFragment.getActivity(), R.animator.menguar);
                                anim.addListener(selectorFragment);
                                anim.setTarget(view);
                                anim.start();
                                // compentado para que compile
                                // adaptador.borrar(id); //adaptador.notifyDataSetChanged();
                            }
                        }).show();
                        break;
                    case 2: //Insertar
                        //int posicion = recyclerView.getChildLayoutPosition(view);
                        adaptador.insertar((Libro) adaptador.getItemByKey(key));
                        //adaptador.notifyDataSetChanged();
                        adaptador.notifyItemInserted(0);
                        Snackbar.make(view, "Libro insertado", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        }).show();
                        break;
                }
            }
        });

        menu.create().show();
    }
}

