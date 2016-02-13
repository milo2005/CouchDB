package com.example.estacionvl_tc_014.couchdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.ManagerOptions;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;
import com.example.estacionvl_tc_014.couchdb.couchdb.LibroDAO;
import com.example.estacionvl_tc_014.couchdb.models.Libro;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Libro l = new Libro();
        l.setAutor("DArio chamorro");
        l.setNombre("Como aburrir con CouchDB un Viernes");
        l.setPag(5000);

        LibroDAO dao =  new LibroDAO(App.db);

        dao.insert(l);
        dao.delete(l);
        dao.update(l);
        l = dao.getLibroById("dsadas");

        List<Libro> libros = dao.getLibrosByAutor("dario Chamorro");

    }
}
