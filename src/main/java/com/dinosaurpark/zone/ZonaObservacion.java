package com.dinosaurpark.zone;

import com.dinosaurpark.model.Dinosaurio;
import com.dinosaurpark.model.Turista;
import java.util.ArrayList;
import java.util.List;

public class ZonaObservacion extends Zona {

    private double totalRecaudado;
    private final double costoEntrada;
    private final List<Dinosaurio> dinosaurios;
    private final String tipoRecinto; // BASIC, PREMIUM, VIP

    public ZonaObservacion(String tipoRecinto, int capacidadMaxima, double costoEntrada) {
        super("Recinto " + tipoRecinto, capacidadMaxima);
        this.tipoRecinto = tipoRecinto;
        this.costoEntrada = costoEntrada;
        this.totalRecaudado = 0.0;
        this.dinosaurios = new ArrayList<>();
    }

    @Override
    public void procesarTurista(Turista turista) {
        turista.gastar(costoEntrada);
        totalRecaudado += costoEntrada;

        System.out.println("🦕 " + turista.getNombre() +
                " entró al recinto " + tipoRecinto +
                " por $" + costoEntrada);

        // El turista observa cada dinosaurio
        for (Dinosaurio d : dinosaurios) {
            System.out.println("   👁️  " + turista.getNombre() +
                    " observa a " + d.getNombre() +
                    " — " + d.hacerSonido());
        }
    }

    public void agregarDinosauro(Dinosaurio dinosaurio) {
        dinosaurios.add(dinosaurio);
        System.out.println("🦖 " + dinosaurio.getNombre() +
                " asignado al " + nombre);
    }

    public List<Dinosaurio> getDinosaurios() { return dinosaurios; }
    public double getTotalRecaudado()        { return totalRecaudado; }
    public String getTipoRecinto()           { return tipoRecinto; }
}