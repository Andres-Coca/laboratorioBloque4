package com.dinosaurpark.event;

import java.time.LocalDateTime;

public abstract class Evento {

    protected TipoEvento tipo;
    protected String descripcion;
    protected LocalDateTime momento;
    protected boolean resuelto;

    public Evento(TipoEvento tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.momento = LocalDateTime.now();
        this.resuelto = false;
    }

    // Cada evento define cómo se resuelve
    public abstract void resolver();

    public void mostrarInfo() {
        System.out.println("⚠️  EVENTO: " + tipo);
        System.out.println("   Descripción: " + descripcion);
        System.out.println("   Momento:     " + momento);
        System.out.println("   Resuelto:    " + (resuelto ? "Sí" : "No"));
    }

    // Getters
    public TipoEvento getTipo()      { return tipo; }
    public String getDescripcion()   { return descripcion; }
    public LocalDateTime getMomento(){ return momento; }
    public boolean isResuelto()      { return resuelto; }
}