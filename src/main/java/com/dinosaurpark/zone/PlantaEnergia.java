package com.dinosaurpark.zone;

import com.dinosaurpark.model.Turista;

public class PlantaEnergia extends Zona {

    private double energiaDisponible;
    private double consumoPorPaso;
    private double probabilidadFalla;
    private double costoMantenimiento;
    private double totalGastos;
    private boolean activa;

    public PlantaEnergia(double energiaInicial, double consumoPorPaso,
                         double probabilidadFalla, double costoMantenimiento) {
        super("Planta de Energía", 0);
        this.energiaDisponible = energiaInicial;
        this.consumoPorPaso = consumoPorPaso;
        this.probabilidadFalla = probabilidadFalla;
        this.costoMantenimiento = costoMantenimiento;
        this.totalGastos = 0.0;
        this.activa = true;
    }

    @Override
    public void procesarTurista(Turista turista) {
        // La planta no procesa turistas directamente
    }

    public void ejecutarPaso() {
        if (!activa) {
            System.out.println("⚡ Planta de energía INACTIVA.");
            return;
        }

        // Consumir energía
        energiaDisponible -= consumoPorPaso;
        System.out.println("⚡ Energía disponible: " + energiaDisponible);

        // Verificar falla aleatoria
        if (Math.random() < probabilidadFalla) {
            activarFalla();
        }

        // Sin energía
        if (energiaDisponible <= 0) {
            energiaDisponible = 0;
            activa = false;
            System.out.println("🔴 ¡Sin energía! El parque se detiene.");
        }
    }

    private void activarFalla() {
        activa = false;
        totalGastos += costoMantenimiento;
        System.out.println("💥 ¡Falla en la planta! Costo: $" + costoMantenimiento);
    }

    public void reparar(double costoReparacion) {
        activa = true;
        energiaDisponible = 50;
        totalGastos += costoReparacion;
        System.out.println("🔧 Planta reparada. Costo: $" + costoReparacion);
    }

    public boolean isActiva()              { return activa; }
    public double getEnergiaDisponible()   { return energiaDisponible; }
    public double getTotalGastos()         { return totalGastos; }
}