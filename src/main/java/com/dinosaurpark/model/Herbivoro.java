package com.dinosaurpark.model;

public class Herbivoro extends Dinosaurio {

    private int nivelCalma;

    public Herbivoro(int id, String nombre, int nivelPeligro, int nivelCalma) {
        super(id, nombre, "Herbívoro", nivelPeligro);
        this.nivelCalma = nivelCalma;
    }

    @Override
    public String hacerSonido() {
        return "...muuu... (" + nombre + " pace tranquilo)";
    }

    public int getNivelCalma() { return nivelCalma; }
}