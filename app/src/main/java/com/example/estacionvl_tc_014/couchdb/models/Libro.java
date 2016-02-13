package com.example.estacionvl_tc_014.couchdb.models;

/**
 * Created by EstacionVL-TC-014 on 12/02/16.
 */
public class Libro extends DocumentModel {

    String nombre;
    String autor;
    int pag;

    public Libro(){
        setType("libro");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        parameters.put("nombre", nombre);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
        parameters.put("autor", autor);
    }

    public int getPag() {
        return pag;
    }

    public void setPag(int pag) {
        this.pag = pag;
        parameters.put("pag", pag);
    }
}
