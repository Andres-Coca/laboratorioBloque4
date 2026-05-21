package com.dinosaurpark;

import com.dinosaurpark.event.*;
import com.dinosaurpark.model.*;
import com.dinosaurpark.zone.PlantaEnergia;
import com.dinosaurpark.zone.ZonaObservacion;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {

    @Test
    void escape_herbivoro_se_resuelve() {
        Herbivoro h = new Herbivoro(1, "Bella", 2, 8);
        EscapeDinosaurio evento = new EscapeDinosaurio(h, new ArrayList<>(), new Random());
        evento.resolver();
        assertTrue(evento.isResuelto());
        assertEquals(DinosaurioEstado.CONTENIDO, h.getEstado());
    }

    @Test
    void escape_carnivoro_se_resuelve() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        EscapeDinosaurio evento = new EscapeDinosaurio(c, new ArrayList<>(), new Random());
        evento.resolver();
        assertTrue(evento.isResuelto());
        assertEquals(DinosaurioEstado.CONTENIDO, c.getEstado());
    }

    @Test
    void carnivoro_puede_atacar_turistas() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        List<Turista> turistas = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            turistas.add(new Turista.Builder()
                    .conId(i).conNombre("Turista-" + i)
                    .conPresupuesto(200.0).construir());
        }
        EscapeDinosaurio evento = new EscapeDinosaurio(c, turistas, new Random(42));
        evento.resolver();
        assertTrue(evento.isResuelto());
    }

    @Test
    void apagon_repara_planta() {
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 0.0, 200.0);
        ApagonMasivo evento = new ApagonMasivo(planta, 500.0);
        evento.resolver();
        assertTrue(evento.isResuelto());
        assertTrue(planta.isActiva());
    }

    @Test
    void tormenta_afecta_zonas() {
        List<com.dinosaurpark.zone.Zona> zonas = new ArrayList<>();
        zonas.add(new ZonaObservacion("BASIC", 20, 10.0));
        zonas.add(new ZonaObservacion("VIP", 5, 75.0));
        TormentaTorrencial evento = new TormentaTorrencial(zonas);
        evento.resolver();
        assertTrue(evento.isResuelto());
    }

    @Test
    void evento_tiene_descripcion() {
        Herbivoro h = new Herbivoro(1, "Bella", 2, 8);
        EscapeDinosaurio evento = new EscapeDinosaurio(h, new ArrayList<>(), new Random());
        assertNotNull(evento.getDescripcion());
        assertFalse(evento.getDescripcion().isEmpty());
    }

    @Test
    void evento_tiene_tipo() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        EscapeDinosaurio evento = new EscapeDinosaurio(c, new ArrayList<>(), new Random());
        assertEquals(TipoEvento.ESCAPE_DINOSAURIO, evento.getTipo());
    }
}