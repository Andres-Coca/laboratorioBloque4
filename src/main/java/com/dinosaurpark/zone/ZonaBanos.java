package com.dinosaurpark.zone;

import com.dinosaurpark.model.Turista;

public class ZonaBanos extends Zona {

    private double totalRecaudado;
    private final double precioSpa;
    private final double probabilidadSpa;

    public ZonaBanos(int capacidadMaxima, double precioSpa, double probabilidadSpa) {
        super("Zona de Baños", capacidadMaxima);
        this.precioSpa = precioSpa;
        this.probabilidadSpa = probabilidadSpa;
        this.totalRecaudado = 0.0;
    }

    @Override
    public void procesarTurista(Turista turista) {
        System.out.println("🚻 " + turista.getNombre() + " usa los baños.");

        // Simulamos si el turista decide usar el SPA
        if (Math.random() < probabilidadSpa) {
            turista.gastar(precioSpa);
            totalRecaudado += precioSpa;
            System.out.println("💆 " + turista.getNombre() +
                    " usó el SPA por $" + precioSpa);
        }
    }

    public double getTotalRecaudado() { return totalRecaudado; }
}