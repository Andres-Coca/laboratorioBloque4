package com.dinosaurpark.model;

public class Carnivoro extends Dinosaurio {

    private int poderAtaque;

    public Carnivoro(int id, String nombre, int nivelPeligro, int poderAtaque) {
        super(id, nombre, "Carnívoro", nivelPeligro);
        this.poderAtaque = poderAtaque;
    }

    @Override
    public String hacerSonido() {
        return "¡ROOOAAAR! (" + nombre + " ruge amenazante)";
    }

    public int getPoderAtaque() { return poderAtaque; }
}