package com.example.audiolibros;

/**
 * Created by AMARTIN on 01/02/2017.
 */

public class MainPresenter {
    private final LibroStorage libroStorage;
    private final SaveLastBook saveLastBook;
    private final View view;

    public MainPresenter(SaveLastBook saveLastBook, LibroStorage libroStorage, MainPresenter.View view) {
        this.saveLastBook = saveLastBook;
        this.libroStorage = libroStorage;
        this.view = view;
    }

    public void clickFavoriteButton() {
        if (libroStorage.hasLastBook()) {
            view.mostrarFragmentDetalle(libroStorage.getLastBook());
        } else {
            view.mostrarNoUltimaVisita();
        }
    }

    public void openDetalle(int id) {
       // libroStorage.saveLastBook(id);
        //view.mostrarDetalle(id);
        saveLastBook.execute(id);
        view.mostrarFragmentDetalle(id);
    }

    public interface View {
        void mostrarFragmentDetalle(int lastBook);

        void mostrarNoUltimaVisita();
    }
}