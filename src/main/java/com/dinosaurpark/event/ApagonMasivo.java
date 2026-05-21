package com.dinosaurpark.event;

import com.dinosaurpark.zone.PlantaEnergia;

public class ApagonMasivo extends Evento {

    private final PlantaEnergia plantaEnergia;
    private final double costoReparacion;

    public ApagonMasivo(PlantaEnergia plantaEnergia, double costoReparacion) {
        super(TipoEvento.APAGON_MASIVO,
                "¡Apagón masivo! Toda la energía del parque se ha cortado.");
        this.plantaEnergia = plantaEnergia;
        this.costoReparacion = costoReparacion;
    }

    @Override
    public void resolver() {
        System.out.println("🔴 ¡APAGÓN! " + descripcion);
        System.out.println("   🔧 Técnicos reparando la planta de energía...");
        plantaEnergia.reparar(costoReparacion);
        resuelto = true;
        System.out.println("   ✅ Energía restaurada.");
    }
}