package com.dinosaurpark.factory;

import com.dinosaurpark.model.Carnivoro;
import com.dinosaurpark.model.Dinosaurio;

public class CarnivoreFactory implements DinosaurioFactory {

    @Override
    public Dinosaurio crear(int id, String nombre, int nivelPeligro) {
        int poderAtaque = nivelPeligro * 2; // entre más peligroso, más ataca
        System.out.println("🦖 Fábrica creando carnívoro: " + nombre);
        return new Carnivoro(id, nombre, nivelPeligro, poderAtaque);
    }
}