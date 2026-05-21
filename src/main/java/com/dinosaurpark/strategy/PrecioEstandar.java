package com.dinosaurpark.strategy;

import com.dinosaurpark.model.Turista;

public class PrecioEstandar implements PrecioStrategy {

    private final double precioBase;

    public PrecioEstandar(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio(Turista turista) {
        return precioBase;
    }

    @Override
    public String describir() {
        return "Precio estándar: $" + precioBase;
    }
}