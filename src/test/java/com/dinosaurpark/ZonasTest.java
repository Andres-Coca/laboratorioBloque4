package com.dinosaurpark;

import com.dinosaurpark.model.*;
import com.dinosaurpark.zone.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZonasTest {

    @Test
    void recinto_central_procesa_turista() {
        RecintoCentral recinto = new RecintoCentral(100, 15.0, 1.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        recinto.procesarTurista(t);
        assertEquals(15.0, recinto.getTotalRecaudado());
    }

    @Test
    void recinto_central_turista_sin_compra() {
        RecintoCentral recinto = new RecintoCentral(100, 15.0, 0.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        recinto.procesarTurista(t);
        assertEquals(0.0, recinto.getTotalRecaudado());
    }

    @Test
    void zona_banos_procesa_turista() {
        ZonaBanos banos = new ZonaBanos(10, 20.0, 0.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        banos.procesarTurista(t);
        assertEquals(0.0, banos.getTotalRecaudado());
    }

    @Test
    void zona_banos_con_spa() {
        ZonaBanos banos = new ZonaBanos(10, 20.0, 1.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        banos.procesarTurista(t);
        assertEquals(20.0, banos.getTotalRecaudado());
    }

    @Test
    void zona_observacion_agrega_dinosaurio() {
        ZonaObservacion zona = new ZonaObservacion("BASIC", 20, 10.0);
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        zona.agregarDinosauro(c);
        assertEquals(1, zona.getDinosaurios().size());
    }

    @Test
    void zona_observacion_procesa_turista() {
        ZonaObservacion zona = new ZonaObservacion("BASIC", 20, 10.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        zona.procesarTurista(t);
        assertEquals(10.0, zona.getTotalRecaudado());
    }

    @Test
    void zona_observacion_turista_ve_dinosaurios() {
        ZonaObservacion zona = new ZonaObservacion("VIP", 5, 75.0);
        Herbivoro h = new Herbivoro(1, "Bella", 2, 8);
        zona.agregarDinosauro(h);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        zona.procesarTurista(t);
        assertEquals(75.0, zona.getTotalRecaudado());
    }

    @Test
    void zona_nombre_correcto() {
        ZonaObservacion zona = new ZonaObservacion("PREMIUM", 12, 30.0);
        assertEquals("Recinto PREMIUM", zona.getNombre());
        assertEquals("PREMIUM", zona.getTipoRecinto());
    }
}