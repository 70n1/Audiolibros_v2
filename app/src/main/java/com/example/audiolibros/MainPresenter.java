package com.example.audiolibros;

/**
 * Created by AMARTIN on 01/02/2017.
 */

public class MainPresenter {
    private final LibroStorage libroStorage;
    private final SaveLastBook saveLastBook;
    private final BooksRespository booksRespository;
    private final View view;

    public MainPresenter(SaveLastBook saveLastBook, LibroStorage libroStorage, MainPresenter.View view) {
        this.saveLastBook = saveLastBook;
        this.libroStorage = libroStorage;
        this. booksRespository = new BooksRespository(libroStorage);
        this.view = view;
    }

    public void clickFavoriteButton() {
        if (libroStorage.hasLastBook()) {
            view.mostrarFragmentDetalle(booksRespository.getLastBook());
        } else {
            view.mostrarNoUltimaVisita();
        }
    }

    public void openDetalle(String key) {
       // libroStorage.saveLastBook(id);
        //view.mostrarDetalle(id);
        saveLastBook.execute(key);
        view.mostrarFragmentDetalle(key);
    }

    public interface View {
        void mostrarFragmentDetalle(String lastBook);

        void mostrarNoUltimaVisita();
    }
}