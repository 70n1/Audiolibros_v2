package com.example.audiolibros;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by AMARTIN on 09/02/2017.
 */

public class Lecturas implements ChildEventListener {
    private String UIDActual;
    private DatabaseReference referenciaMisLecturas;
    private ArrayList<String> idLibros;


    public  void Lecturas(){
        UIDActual = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenciaMisLecturas = database.getReference().child("lecturas").child(UIDActual);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void activaEscuchadorMisLecturas() {
        referenciaMisLecturas.addChildEventListener(this);
    }

    public void desactivaEscuchadorMisLecturas() {
        referenciaMisLecturas.removeEventListener(this);
    }
}
