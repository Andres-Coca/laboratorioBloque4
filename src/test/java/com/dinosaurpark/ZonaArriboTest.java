package com.dinosaurpark;

import com.dinosaurpark.model.TipoTicket;
import com.dinosaurpark.model.Turista;
import com.dinosaurpark.zone.ZonaArribo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZonaArriboTest {

    @Test
    void zona_no_esta_llena_al_inicio() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        assertFalse(zona.estaLlena());
    }

    @Test
    void zona_acepta_turista() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0)
                .construir();
        assertTrue(zona.agregarTurista(t));
        assertEquals(1, zona.getTuristasActuales());
    }

    @Test
    void zona_llena_no_acepta_mas_turistas() {
        ZonaArribo zona = new ZonaArribo(1, 25.0);
        Turista t1 = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();
        Turista t2 = new Turista.Builder()
                .conId(2).conNombre("Ana")
                .conPresupuesto(200.0).construir();

        zona.agregarTurista(t1);
        assertFalse(zona.agregarTurista(t2));
    }

    @Test
    void zona_procesa_turista_estandar() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conTipoTicket(TipoTicket.ESTANDAR)
                .conPresupuesto(200.0).construir();

        zona.procesarTurista(t);
        assertEquals(25.0, zona.getTotalRecaudado());
    }

    @Test
    void zona_procesa_turista_premium() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Ana")
                .conTipoTicket(TipoTicket.PREMIUM)
                .conPresupuesto(200.0).construir();

        zona.procesarTurista(t);
        assertEquals(37.5, zona.getTotalRecaudado());
    }

    @Test
    void zona_procesa_turista_vip() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Carlos")
                .conTipoTicket(TipoTicket.VIP)
                .conPresupuesto(200.0).construir();

        zona.procesarTurista(t);
        assertEquals(75.0, zona.getTotalRecaudado());
    }

    @Test
    void zona_remueve_turista() {
        ZonaArribo zona = new ZonaArribo(30, 25.0);
        Turista t = new Turista.Builder()
                .conId(1).conNombre("Juan")
                .conPresupuesto(200.0).construir();

        zona.agregarTurista(t);
        assertEquals(1, zona.getTuristasActuales());
        zona.removerTurista(t);
        assertEquals(0, zona.getTuristasActuales());
    }
}