package com.example.estacionvl_tc_014.couchdb;

import android.app.Application;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;
import java.util.Map;

/**
 * Created by EstacionVL-TC-014 on 12/02/16.
 */
public class App extends Application {

    public static final String VIEW_TYPE="type";
    public static final String VIEW_AUTOR="autor";

    public static Database db;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Manager manager = new Manager(new AndroidContext(this)
                    , Manager.DEFAULT_OPTIONS);

             db = manager.getDatabase("libro");

            //Consulta que me retorne todos los documentos de un tipo

            View type = db.getView(VIEW_TYPE);
            type.setMap(new Mapper() {
                @Override
                public void map(Map<String, Object> document, Emitter emitter) {

                    emitter.emit(document.get("type"),document);

                }
            },"1");

            //Todos los libros de un autor

            View autor =  db.getView(VIEW_AUTOR);
            autor.setMap(new Mapper() {
                @Override
                public void map(Map<String, Object> document, Emitter emitter) {
                    if(document.get("type").equals("libro")){
                        emitter.emit(document.get("autor"), document);
                    }
                }
            },"1");



        } catch (IOException e) {
            e.printStackTrace();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

    }
}
