package com.dinosaurpark.monitoring;

import com.dinosaurpark.zone.PlantaEnergia;
import com.dinosaurpark.zone.ZonaArribo;
import com.dinosaurpark.zone.ZonaObservacion;

import java.util.List;

public class MonitorParque {

    private final ZonaArribo zonaArribo;
    private final PlantaEnergia plantaEnergia;
    private final List<ZonaObservacion> zonasObservacion;
    private int turistasActivos;
    private int totalEventos;

    public MonitorParque(ZonaArribo zonaArribo,
                         PlantaEnergia plantaEnergia,
                         List<ZonaObservacion> zonasObservacion) {
        this.zonaArribo       = zonaArribo;
        this.plantaEnergia    = plantaEnergia;
        this.zonasObservacion = zonasObservacion;
        this.turistasActivos  = 0;
        this.totalEventos     = 0;
    }

    public void registrarEvento() {
        totalEventos++;
    }

    public void actualizarTuristas(int cantidad) {
        turistasActivos = cantidad;
    }

    public void mostrarEstado() {
        System.out.println("\n========================================");
        System.out.println("        MONITOR DEL PARQUE");
        System.out.println("========================================");
        System.out.println("👥 Turistas activos:    " + turistasActivos);
        System.out.println("⚡ Energía disponible:  " + plantaEnergia.getEnergiaDisponible());
        System.out.println("🔌 Planta activa:       " + (plantaEnergia.isActiva() ? "Sí" : "No"));
        System.out.println("💰 Total recaudado:     $" + zonaArribo.getTotalRecaudado());
        System.out.println("⚠️  Eventos ocurridos:  " + totalEventos);
        System.out.println("🦕 Dinosaurios en recintos:");
        for (ZonaObservacion zona : zonasObservacion) {
            System.out.println("   - " + zona.getNombre() +
                    ": " + zona.getDinosaurios().size() + " dinosaurios");
        }
        System.out.println("========================================\n");
    }
}