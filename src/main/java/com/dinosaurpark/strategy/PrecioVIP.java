package com.dinosaurpark.strategy;

import com.dinosaurpark.model.Turista;

public class PrecioVIP implements PrecioStrategy {

    private final double precioBase;

    public PrecioVIP(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio(Turista turista) {
        return precioBase * 3.0; // triple precio
    }

    @Override
    public String describir() {
        return "Precio VIP: $" + (precioBase * 3.0);
    }
}