package com.dinosaurpark.model;

public abstract class Dinosaurio {

    protected int id;
    protected String nombre;
    protected String tipo;
    protected int nivelPeligro;
    protected DinosaurioEstado estado;
    protected boolean alimentado;

    public Dinosaurio(int id, String nombre, String tipo, int nivelPeligro) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivelPeligro = nivelPeligro;
        this.estado = DinosaurioEstado.CALMADO;
        this.alimentado = false;
    }

    // Cada subclase DEBE implementar su propio sonido
    public abstract String hacerSonido();

    public void alimentar() {
        this.alimentado = true;
        this.estado = DinosaurioEstado.CALMADO;
        System.out.println(nombre + " ha sido alimentado.");
    }

    public void mostrarInformacion() {
        System.out.println("=== DINOSAURIO ===");
        System.out.println("ID:            " + id);
        System.out.println("Nombre:        " + nombre);
        System.out.println("Tipo:          " + tipo);
        System.out.println("Nivel peligro: " + nivelPeligro);
        System.out.println("Estado:        " + estado);
        System.out.println("Alimentado:    " + (alimentado ? "Sí" : "No"));
    }

    // Getters
    public int getId()                   { return id; }
    public String getNombre()            { return nombre; }
    public String getTipo()              { return tipo; }
    public int getNivelPeligro()         { return nivelPeligro; }
    public DinosaurioEstado getEstado()  { return estado; }
    public boolean isAlimentado()        { return alimentado; }

    // Setters
    public void setEstado(DinosaurioEstado estado) { this.estado = estado; }
    public void setAlimentado(boolean alimentado)  { this.alimentado = alimentado; }
}