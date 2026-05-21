package com.dinosaurpark;

import com.dinosaurpark.model.*;
import com.dinosaurpark.factory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DinosaurioTest {

    @Test
    void carnivoro_creado_correctamente() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        assertEquals(1, c.getId());
        assertEquals("Rex", c.getNombre());
        assertEquals("Carnívoro", c.getTipo());
        assertEquals(8, c.getNivelPeligro());
        assertEquals(16, c.getPoderAtaque());
    }

    @Test
    void herbivoro_creado_correctamente() {
        Herbivoro h = new Herbivoro(2, "Bella", 2, 8);
        assertEquals(2, h.getId());
        assertEquals("Bella", h.getNombre());
        assertEquals("Herbívoro", h.getTipo());
        assertEquals(2, h.getNivelPeligro());
        assertEquals(8, h.getNivelCalma());
    }

    @Test
    void dinosaurio_estado_inicial_es_calmado() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        assertEquals(DinosaurioEstado.CALMADO, c.getEstado());
        assertFalse(c.isAlimentado());
    }

    @Test
    void dinosaurio_alimentado_cambia_estado() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        c.alimentar();
        assertTrue(c.isAlimentado());
        assertEquals(DinosaurioEstado.CALMADO, c.getEstado());
    }

    @Test
    void dinosaurio_cambiar_estado() {
        Herbivoro h = new Herbivoro(1, "Bella", 2, 8);
        h.setEstado(DinosaurioEstado.ESCAPADO);
        assertEquals(DinosaurioEstado.ESCAPADO, h.getEstado());
    }

    @Test
    void carnivoro_factory_crea_correctamente() {
        CarnivoreFactory factory = new CarnivoreFactory();
        var dino = factory.crear(1, "Rex", 8);
        assertNotNull(dino);
        assertEquals("Rex", dino.getNombre());
        assertEquals("Carnívoro", dino.getTipo());
    }

    @Test
    void herbivoro_factory_crea_correctamente() {
        HerbivoroFactory factory = new HerbivoroFactory();
        var dino = factory.crear(1, "Bella", 2);
        assertNotNull(dino);
        assertEquals("Bella", dino.getNombre());
        assertEquals("Herbívoro", dino.getTipo());
    }

    @Test
    void carnivoro_hace_sonido() {
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        assertTrue(c.hacerSonido().contains("ROOOAAAR"));
    }

    @Test
    void herbivoro_hace_sonido() {
        Herbivoro h = new Herbivoro(1, "Bella", 2, 8);
        assertTrue(h.hacerSonido().contains("muuu"));
    }
}