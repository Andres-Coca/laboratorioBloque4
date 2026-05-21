package com.dinosaurpark.strategy;

import com.dinosaurpark.model.Turista;

public interface PrecioStrategy {
    double calcularPrecio(Turista turista);
    String describir();
}