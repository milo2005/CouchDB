package com.example.estacionvl_tc_014.couchdb.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EstacionVL-TC-014 on 12/02/16.
 */
public class DocumentModel {

    String id;
    String type;
    Map<String, Object> parameters;

    public DocumentModel(){
        parameters =  new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        parameters.put("_id", id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        parameters.put("type", type);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
