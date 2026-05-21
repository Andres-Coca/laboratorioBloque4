package com.dinosaurpark;

import com.dinosaurpark.zone.PlantaEnergia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlantaEnergiaTest {

    @Test
    void planta_activa_al_inicio() {
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 0.0, 200.0);
        assertTrue(planta.isActiva());
        assertEquals(100.0, planta.getEnergiaDisponible());
    }

    @Test
    void planta_consume_energia_por_paso() {
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 0.0, 200.0);
        planta.ejecutarPaso();
        assertEquals(98.5, planta.getEnergiaDisponible());
    }

    @Test
    void planta_se_apaga_sin_energia() {
        PlantaEnergia planta = new PlantaEnergia(1.0, 5.0, 0.0, 200.0);
        planta.ejecutarPaso();
        assertFalse(planta.isActiva());
        assertEquals(0.0, planta.getEnergiaDisponible());
    }

    @Test
    void planta_reparada_queda_activa() {
        PlantaEnergia planta = new PlantaEnergia(1.0, 5.0, 0.0, 200.0);
        planta.ejecutarPaso();
        assertFalse(planta.isActiva());
        planta.reparar(500.0);
        assertTrue(planta.isActiva());
        assertEquals(50.0, planta.getEnergiaDisponible());
    }

    @Test
    void planta_registra_gastos_de_reparacion() {
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 0.0, 200.0);
        planta.reparar(500.0);
        assertEquals(500.0, planta.getTotalGastos());
    }

    @Test
    void planta_inactiva_no_consume_energia() {
        PlantaEnergia planta = new PlantaEnergia(1.0, 5.0, 0.0, 200.0);
        planta.ejecutarPaso(); // se apaga
        double energiaAntes = planta.getEnergiaDisponible();
        planta.ejecutarPaso(); // no debe consumir
        assertEquals(energiaAntes, planta.getEnergiaDisponible());
    }

    @Test
    void planta_falla_con_probabilidad_uno() {
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 1.0, 200.0);
        planta.ejecutarPaso();
        assertFalse(planta.isActiva());
        assertEquals(200.0, planta.getTotalGastos());
    }
}