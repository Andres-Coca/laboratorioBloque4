package com.dinosaurpark.event;

import com.dinosaurpark.zone.Zona;
import java.util.List;

public class TormentaTorrencial extends Evento {

    private final List<Zona> zonasAfectadas;

    public TormentaTorrencial(List<Zona> zonasAfectadas) {
        super(TipoEvento.TORMENTA_TORRENCIAL,
                "¡Tormenta torrencial! Varias zonas del parque están afectadas.");
        this.zonasAfectadas = zonasAfectadas;
    }

    @Override
    public void resolver() {
        System.out.println("🌧️  ¡TORMENTA! " + descripcion);
        System.out.println("   Zonas afectadas:");
        for (Zona zona : zonasAfectadas) {
            System.out.println("   ⚠️  " + zona.getNombre() + " temporalmente cerrada.");
        }
        System.out.println("   ⏳ Esperando que pase la tormenta...");
        resuelto = true;
        System.out.println("   ✅ Tormenta superada. Zonas reabiertas.");
    }
}