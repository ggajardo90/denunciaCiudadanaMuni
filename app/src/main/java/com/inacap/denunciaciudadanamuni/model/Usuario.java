package com.inacap.denunciaciudadanamuni.model;

public class Usuario {

    private String email;
    private String nombre;
    public  String celular;
    public  String  clave;
    private String uid;


    public Usuario(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Usuario(String email, String nombre, String celular, String clave, String uid) {
        this.email = email;
        this.nombre = nombre;
        this.celular = celular;
        this.clave = clave;
        this.uid = uid;
    }




}
