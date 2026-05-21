package com.dinosaurpark.persistence;

import java.time.LocalDateTime;

public class RegistroGasto {

    private String concepto;
    private double monto;
    private String zona;
    private String momento;

    public RegistroGasto(String concepto, double monto, String zona) {
        this.concepto = concepto;
        this.monto = monto;
        this.zona = zona;
        this.momento = LocalDateTime.now().toString();
    }

    // Getters
    public String getConcepto() { return concepto; }
    public double getMonto()    { return monto; }
    public String getZona()     { return zona; }
    public String getMomento()  { return momento; }
}