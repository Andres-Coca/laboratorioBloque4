package com.dinosaurpark.zone;

import com.dinosaurpark.model.Turista;
import java.util.ArrayList;
import java.util.List;

public abstract class Zona {

    protected String nombre;
    protected int capacidadMaxima;
    protected List<Turista> turistasActuales;

    public Zona(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.turistasActuales = new ArrayList<>();
    }

    // Cada zona define qué hace cuando entra un turista
    public abstract void procesarTurista(Turista turista);

    public boolean estaLlena() {
        return turistasActuales.size() >= capacidadMaxima;
    }

    public boolean agregarTurista(Turista turista) {
        if (estaLlena()) {
            System.out.println("⚠️  " + nombre + " está llena.");
            return false;
        }
        turistasActuales.add(turista);
        return true;
    }

    public void removerTurista(Turista turista) {
        turistasActuales.remove(turista);
    }

    public void mostrarEstado() {
        System.out.println("📍 " + nombre +
                " — Turistas: " + turistasActuales.size() +
                "/" + capacidadMaxima);
    }

    // Getters
    public String getNombre()            { return nombre; }
    public int getCapacidadMaxima()      { return capacidadMaxima; }
    public int getTuristasActuales()     { return turistasActuales.size(); }
}