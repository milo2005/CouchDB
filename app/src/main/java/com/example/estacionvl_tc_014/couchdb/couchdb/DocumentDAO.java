package com.example.estacionvl_tc_014.couchdb.couchdb;

import android.provider.ContactsContract;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.example.estacionvl_tc_014.couchdb.models.DocumentModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by EstacionVL-TC-014 on 12/02/16.
 */
public class DocumentDAO {

    Database db;

    public DocumentDAO(Database db) {
        this.db = db;
    }

    public void insert(DocumentModel doc){
        Document d = db.createDocument();
        try {
            d.putProperties(doc.getParameters());
            doc.setId(d.getId());
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public void delete(DocumentModel doc){
        Document d = db.getDocument(doc.getId());
        try {
            d.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public void update(DocumentModel doc){
        Document d = db.getDocument(doc.getId());
        HashMap<String, Object> oldP = (HashMap<String, Object>) d.getProperties();
        HashMap<String, Object> newP = (HashMap<String, Object>) doc.getParameters();

        Iterator<String> i = newP.keySet().iterator();
        while(i.hasNext()){
            String key = i.next();
            oldP.put(key , newP.get(key));
        }

        try {
            d.putProperties(oldP);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    protected List<Map<String, Object>> query(String view,List<Object> keys, boolean decending, int limit, int skip){
        List<Map<String,Object>> data = new ArrayList<>();

        View v = db.getView(view);
        Query q = v.createQuery();

        q.setDescending(decending);
        if(limit!=0)
            q.setLimit(limit);
        if(skip!=0)
            q.setSkip(skip);

        q.setKeys(keys);

        try {
            QueryEnumerator qe = q.run();
            Iterator<QueryRow> i = qe.iterator();

            while (i.hasNext()){
                QueryRow row = i.next();
                data.add(row.getDocument().getProperties());
            }




        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return data;

    }

    protected Map<String,Object> document(String id){
        Map<String,Object> doc = new HashMap<>();
        Document d = db.getDocument(id);

        doc.putAll(d.getProperties());
        return  doc;

    }



}
