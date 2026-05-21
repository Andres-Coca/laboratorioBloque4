package com.dinosaurpark;

import com.dinosaurpark.config.ParkConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkConfigTest {

    @Test
    void singleton_siempre_devuelve_la_misma_instancia() {
        ParkConfig instancia1 = ParkConfig.getInstance();
        ParkConfig instancia2 = ParkConfig.getInstance();
        assertSame(instancia1, instancia2);
    }

    @Test
    void lee_tourists_del_properties() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(50, config.getTourists());
    }

    @Test
    void lee_carnivores_del_properties() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(5, config.getCarnivores());
    }

    @Test
    void lee_herbivores_del_properties() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(15, config.getHerbivores());
    }

    @Test
    void retorna_default_si_clave_no_existe() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(99, config.getInt("clave.inexistente", 99));
        assertEquals("default", config.getString("clave.inexistente", "default"));
        assertEquals(9.9, config.getDouble("clave.inexistente", 9.9));
    }

    @Test
    void lee_seed_correctamente() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(42L, config.getSeed());
    }

    @Test
    void lee_total_steps_correctamente() {
        ParkConfig config = ParkConfig.getInstance();
        assertEquals(100, config.getTotalSteps());
    }
}