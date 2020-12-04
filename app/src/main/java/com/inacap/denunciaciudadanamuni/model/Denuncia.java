package com.inacap.denunciaciudadanamuni.model;

import com.google.firebase.database.DataSnapshot;

public class Denuncia {
    public String id;
    public String titulo;
    public String direccion;
    public String estado;


    public Denuncia(){

    }


    public Denuncia(String id, String titulo, String direccion, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.direccion = direccion;
        this.estado = estado;

    }

    public String getTitulo() {
        return titulo;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}



