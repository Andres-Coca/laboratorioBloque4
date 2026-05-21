package com.dinosaurpark;

import com.dinosaurpark.model.TipoTicket;
import com.dinosaurpark.model.Turista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TuristaTest {

    @Test
    void turista_creado_correctamente() {
        Turista t = new Turista.Builder()
                .conId(1)
                .conNombre("Juan")
                .conEdad(25)
                .conTipoTicket(TipoTicket.ESTANDAR)
                .conPresupuesto(200.0)
                .construir();

        assertEquals(1, t.getId());
        assertEquals("Juan", t.getNombre());
        assertEquals(25, t.getEdad());
        assertEquals(TipoTicket.ESTANDAR, t.getTipoTicket());
        assertEquals(200.0, t.getPresupuesto());
        assertEquals(0.0, t.getDineroGastado());
    }

    @Test
    void turista_gasta_dinero_correctamente() {
        Turista t = new Turista.Builder()
                .conId(1)
                .conNombre("Ana")
                .conPresupuesto(100.0)
                .construir();

        t.gastar(50.0);
        assertEquals(50.0, t.getDineroGastado());
    }

    @Test
    void turista_no_puede_gastar_mas_de_su_presupuesto() {
        Turista t = new Turista.Builder()
                .conId(1)
                .conNombre("Pedro")
                .conPresupuesto(30.0)
                .construir();

        t.gastar(100.0);
        assertEquals(0.0, t.getDineroGastado());
    }

    @Test
    void turista_puede_ser_herido() {
        Turista t = new Turista.Builder()
                .conId(1)
                .conNombre("Carlos")
                .conPresupuesto(100.0)
                .construir();

        assertFalse(t.isHerido());
        t.herir();
        assertTrue(t.isHerido());
    }
}