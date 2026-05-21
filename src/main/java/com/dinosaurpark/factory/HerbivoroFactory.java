package com.dinosaurpark.factory;

import com.dinosaurpark.model.Dinosaurio;
import com.dinosaurpark.model.Herbivoro;

public class HerbivoroFactory implements DinosaurioFactory {

    @Override
    public Dinosaurio crear(int id, String nombre, int nivelPeligro) {
        int nivelCalma = 10 - nivelPeligro; // menos peligroso = más calmado
        System.out.println("🌿 Fábrica creando herbívoro: " + nombre);
        return new Herbivoro(id, nombre, nivelPeligro, nivelCalma);
    }
}