package com.dinosaurpark.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorJSON {

    private final ObjectMapper mapper;
    private final String directorioSalida;

    private final List<RegistroIngreso> ingresos;
    private final List<RegistroGasto>   gastos;
    private final List<RegistroEvento>  eventos;

    public GestorJSON(String directorioSalida) {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.directorioSalida = directorioSalida;
        this.ingresos = new ArrayList<>();
        this.gastos   = new ArrayList<>();
        this.eventos  = new ArrayList<>();
        new File(directorioSalida).mkdirs();
    }

    public void agregarIngreso(RegistroIngreso ingreso) {
        ingresos.add(ingreso);
    }

    public void agregarGasto(RegistroGasto gasto) {
        gastos.add(gasto);
    }

    public void agregarEvento(RegistroEvento evento) {
        eventos.add(evento);
    }

    public void guardarTodo() {
        guardarIngresos();
        guardarGastos();
        guardarEventos();
        exportarCSV();
    }

    private void guardarIngresos() {
        try {
            mapper.writeValue(new File(directorioSalida + "/ingresos.json"), ingresos);
            System.out.println("💾 ingresos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando ingresos: " + e.getMessage());
        }
    }

    private void guardarGastos() {
        try {
            mapper.writeValue(new File(directorioSalida + "/gastos.json"), gastos);
            System.out.println("💾 gastos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando gastos: " + e.getMessage());
        }
    }

    private void guardarEventos() {
        try {
            mapper.writeValue(new File(directorioSalida + "/eventos.json"), eventos);
            System.out.println("💾 eventos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando eventos: " + e.getMessage());
        }
    }

    private void exportarCSV() {
        exportarIngresosCsv();
        exportarGastosCsv();
        exportarEventosCsv();
    }

    private void exportarIngresosCsv() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(
                new File(directorioSalida + "/revenues.csv"))) {
            pw.println("concepto,monto,turista,momento");
            for (RegistroIngreso r : ingresos) {
                pw.println(r.getConcepto() + "," +
                        r.getMonto() + "," +
                        r.getNombreTurista() + "," +
                        r.getMomento());
            }
            System.out.println("💾 revenues.csv guardado.");
        } catch (Exception e) {
            System.out.println("⚠️  Error guardando revenues.csv: " + e.getMessage());
        }
    }

    private void exportarGastosCsv() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(
                new File(directorioSalida + "/expenses.csv"))) {
            pw.println("concepto,monto,zona,momento");
            for (RegistroGasto r : gastos) {
                pw.println(r.getConcepto() + "," +
                        r.getMonto() + "," +
                        r.getZona() + "," +
                        r.getMomento());
            }
            System.out.println("💾 expenses.csv guardado.");
        } catch (Exception e) {
            System.out.println("⚠️  Error guardando expenses.csv: " + e.getMessage());
        }
    }

    private void exportarEventosCsv() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(
                new File(directorioSalida + "/events.csv"))) {
            pw.println("tipo,descripcion,momento,resuelto");
            for (RegistroEvento r : eventos) {
                pw.println(r.getTipo() + "," +
                        r.getDescripcion() + "," +
                        r.getMomento() + "," +
                        r.isResuelto());
            }
            System.out.println("💾 events.csv guardado.");
        } catch (Exception e) {
            System.out.println("⚠️  Error guardando events.csv: " + e.getMessage());
        }
    }
}