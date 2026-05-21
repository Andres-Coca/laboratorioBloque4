package com.dinosaurpark;

import com.dinosaurpark.event.*;
import com.dinosaurpark.model.Carnivoro;
import com.dinosaurpark.persistence.*;
import com.dinosaurpark.zone.PlantaEnergia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaTest {

    @TempDir
    Path tempDir;

    @Test
    void gestor_crea_directorio_output() {
        String dir = tempDir.resolve("output").toString();
        new GestorJSON(dir);
        assertTrue(new File(dir).exists());
    }

    @Test
    void gestor_guarda_ingresos_json() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        gestor.agregarIngreso(new RegistroIngreso("Boleto", 25.0, "Juan"));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/ingresos.json").exists());
    }

    @Test
    void gestor_guarda_gastos_json() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        gestor.agregarGasto(new RegistroGasto("Mantenimiento", 200.0, "Planta"));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/gastos.json").exists());
    }

    @Test
    void gestor_guarda_eventos_json() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        Carnivoro c = new Carnivoro(1, "Rex", 8, 16);
        EscapeDinosaurio evento = new EscapeDinosaurio(c, new ArrayList<>(), new java.util.Random());
        evento.resolver();
        gestor.agregarEvento(new RegistroEvento(evento));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/eventos.json").exists());
    }

    @Test
    void gestor_guarda_revenues_csv() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        gestor.agregarIngreso(new RegistroIngreso("Boleto", 25.0, "Juan"));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/revenues.csv").exists());
    }

    @Test
    void gestor_guarda_expenses_csv() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        gestor.agregarGasto(new RegistroGasto("Reparacion", 500.0, "Planta"));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/expenses.csv").exists());
    }

    @Test
    void gestor_guarda_events_csv() {
        String dir = tempDir.resolve("output").toString();
        GestorJSON gestor = new GestorJSON(dir);
        PlantaEnergia planta = new PlantaEnergia(100.0, 1.5, 0.0, 200.0);
        ApagonMasivo evento = new ApagonMasivo(planta, 500.0);
        evento.resolver();
        gestor.agregarEvento(new RegistroEvento(evento));
        gestor.guardarTodo();
        assertTrue(new File(dir + "/events.csv").exists());
    }

    @Test
    void registro_ingreso_tiene_datos() {
        RegistroIngreso r = new RegistroIngreso("Boleto VIP", 75.0, "Ana");
        assertEquals("Boleto VIP", r.getConcepto());
        assertEquals(75.0, r.getMonto());
        assertEquals("Ana", r.getNombreTurista());
        assertNotNull(r.getMomento());
    }

    @Test
    void registro_gasto_tiene_datos() {
        RegistroGasto r = new RegistroGasto("Mantenimiento", 200.0, "Planta");
        assertEquals("Mantenimiento", r.getConcepto());
        assertEquals(200.0, r.getMonto());
        assertEquals("Planta", r.getZona());
        assertNotNull(r.getMomento());
    }
}