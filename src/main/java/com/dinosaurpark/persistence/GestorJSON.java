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

        // Crear carpeta output si no existe
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
    }

    private void guardarIngresos() {
        try {
            mapper.writeValue(
                    new File(directorioSalida + "/ingresos.json"),
                    ingresos
            );
            System.out.println("💾 ingresos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando ingresos: " + e.getMessage());
        }
    }

    private void guardarGastos() {
        try {
            mapper.writeValue(
                    new File(directorioSalida + "/gastos.json"),
                    gastos
            );
            System.out.println("💾 gastos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando gastos: " + e.getMessage());
        }
    }

    private void guardarEventos() {
        try {
            mapper.writeValue(
                    new File(directorioSalida + "/eventos.json"),
                    eventos
            );
            System.out.println("💾 eventos.json guardado.");
        } catch (IOException e) {
            System.out.println("⚠️  Error guardando eventos: " + e.getMessage());
        }
    }
}