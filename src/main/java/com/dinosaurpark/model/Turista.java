package com.dinosaurpark.model;

public class Turista {

    private int id;
    private String nombre;
    private int edad;
    private TipoTicket tipoTicket;
    private double presupuesto;
    private double dineroGastado;
    private boolean herido;

    // Constructor PRIVADO — solo el Builder puede crear Turistas
    private Turista() {}

    public void mostrarInformacion() {
        System.out.println("=== TURISTA ===");
        System.out.println("ID:          " + id);
        System.out.println("Nombre:      " + nombre);
        System.out.println("Edad:        " + edad);
        System.out.println("Ticket:      " + tipoTicket);
        System.out.println("Presupuesto: $" + presupuesto);
        System.out.println("Gastado:     $" + dineroGastado);
    }

    public void gastar(double cantidad) {
        if (cantidad <= (presupuesto - dineroGastado)) {
            dineroGastado += cantidad;
            System.out.println(nombre + " gastó $" + cantidad);
        } else {
            System.out.println(nombre + " no tiene suficiente dinero.");
        }
    }

    public void herir() {
        this.herido = true;
        System.out.println("🩸 ¡" + nombre + " fue atacado!");
    }

    public boolean isHerido() { return herido; }

    // Getters
    public int getId()               { return id; }
    public String getNombre()        { return nombre; }
    public int getEdad()             { return edad; }
    public TipoTicket getTipoTicket(){ return tipoTicket; }
    public double getPresupuesto()   { return presupuesto; }
    public double getDineroGastado() { return dineroGastado; }

    // ─── BUILDER (clase interna) ───────────────────────
    public static class Builder {

        private int id;
        private String nombre;
        private int edad;
        private TipoTicket tipoTicket = TipoTicket.ESTANDAR;
        private double presupuesto = 100.0;

        public Builder conId(int id) {
            this.id = id;
            return this;
        }

        public Builder conNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder conEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Builder conTipoTicket(TipoTicket tipoTicket) {
            this.tipoTicket = tipoTicket;
            return this;
        }

        public Builder conPresupuesto(double presupuesto) {
            this.presupuesto = presupuesto;
            return this;
        }

        public Turista construir() {
            Turista t = new Turista();
            t.id = this.id;
            t.nombre = this.nombre;
            t.edad = this.edad;
            t.tipoTicket = this.tipoTicket;
            t.presupuesto = this.presupuesto;
            t.dineroGastado = 0.0;
            return t;
        }
    }
}