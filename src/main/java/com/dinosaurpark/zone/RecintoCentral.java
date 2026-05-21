package com.dinosaurpark.zone;

import com.dinosaurpark.model.Turista;

public class RecintoCentral extends Zona {

    private double totalRecaudado;
    private final double precioSouvenir;
    private final double probabilidadCompra;

    public RecintoCentral(int capacidadMaxima, double precioSouvenir, double probabilidadCompra) {
        super("Recinto Central", capacidadMaxima);
        this.precioSouvenir = precioSouvenir;
        this.probabilidadCompra = probabilidadCompra;
        this.totalRecaudado = 0.0;
    }

    @Override
    public void procesarTurista(Turista turista) {
        // Simulamos si el turista decide comprar souvenir
        if (Math.random() < probabilidadCompra) {
            turista.gastar(precioSouvenir);
            totalRecaudado += precioSouvenir;
            System.out.println("🛍️  " + turista.getNombre() +
                    " compró un souvenir por $" + precioSouvenir);
        } else {
            System.out.println("👀 " + turista.getNombre() +
                    " visitó el recinto central sin comprar.");
        }
    }

    public double getTotalRecaudado() { return totalRecaudado; }
}