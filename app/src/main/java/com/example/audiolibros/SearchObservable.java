package com.example.audiolibros;

import java.util.Observable;

import android.widget.SearchView;

/**
 * Created by AMARTIN on 30/01/2017.
 */

public class SearchObservable extends Observable implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextChange(String query) {
        setChanged();
        notifyObservers(query);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
