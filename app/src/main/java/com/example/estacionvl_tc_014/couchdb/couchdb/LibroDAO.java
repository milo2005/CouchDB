package com.example.estacionvl_tc_014.couchdb.couchdb;

import com.couchbase.lite.Database;
import com.example.estacionvl_tc_014.couchdb.App;
import com.example.estacionvl_tc_014.couchdb.models.Libro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EstacionVL-TC-014 on 12/02/16.
 */
public class LibroDAO extends DocumentDAO {

    public LibroDAO(Database db) {
        super(db);
    }

    public Libro getLibroById(String id){
        HashMap<String, Object> p = (HashMap<String, Object>) document(id);
        Libro l = null;

        if(p.size()>0)
            l = mapToLibro(p);
        return  l;

    }

    public List<Libro> getLibrosByAutor(String autor){
        List<Libro> data =  new ArrayList<>();

        List<Object> key =  new ArrayList<>();
        key.add(autor);

        List<Map<String,Object>> q = query(App.VIEW_AUTOR,key, false,0,0);

        for(Map<String,Object> map :q){
            Libro l = mapToLibro(map);
            data.add(l);
        }

        return data;

    }

    private Libro mapToLibro(Map<String,Object> map){
        Libro l =  new Libro();
        l.setId((String) map.get("id"));
        l.setAutor((String) map.get("autor"));
        l.setNombre((String) map.get("nombre"));
        l.setPag((Integer) map.get("pag"));
        return  l;

    }

}
