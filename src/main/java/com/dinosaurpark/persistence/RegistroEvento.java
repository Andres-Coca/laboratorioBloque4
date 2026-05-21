package com.dinosaurpark.persistence;

import com.dinosaurpark.event.Evento;

public class RegistroEvento {

    private String tipo;
    private String descripcion;
    private String momento;
    private boolean resuelto;

    public RegistroEvento(Evento evento) {
        this.tipo = evento.getTipo().toString();
        this.descripcion = evento.getDescripcion();
        this.momento = evento.getMomento().toString();
        this.resuelto = evento.isResuelto();
    }

    // Getters
    public String getTipo()        { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getMomento()     { return momento; }
    public boolean isResuelto()    { return resuelto; }
}