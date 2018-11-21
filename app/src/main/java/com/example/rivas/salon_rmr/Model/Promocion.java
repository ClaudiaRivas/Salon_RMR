package com.example.rivas.salon_rmr.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Promocion implements Serializable {
    private String id;
    private String nombre;
    private String descripcion;
    private String precio;
    private String ruta_imagen;
    public transient Bitmap imagenProducto;

    public Promocion(){

        ruta_imagen = null;
        imagenProducto =null;
    }

    public Promocion(String id, String nombre, String descripcion, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
