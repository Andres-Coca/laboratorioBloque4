package com.dinosaurpark.persistence;

import java.time.LocalDateTime;

public class RegistroIngreso {

    private String concepto;
    private double monto;
    private String nombreTurista;
    private String momento;

    public RegistroIngreso(String concepto, double monto, String nombreTurista) {
        this.concepto = concepto;
        this.monto = monto;
        this.nombreTurista = nombreTurista;
        this.momento = LocalDateTime.now().toString();
    }

    // Getters
    public String getConcepto()      { return concepto; }
    public double getMonto()         { return monto; }
    public String getNombreTurista() { return nombreTurista; }
    public String getMomento()       { return momento; }
}