package com.dinosaurpark.strategy;

import com.dinosaurpark.model.Turista;

public class PrecioPremium implements PrecioStrategy {

    private final double precioBase;

    public PrecioPremium(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio(Turista turista) {
        return precioBase * 1.5; // 50% más caro
    }

    @Override
    public String describir() {
        return "Precio premium: $" + (precioBase * 1.5);
    }
}