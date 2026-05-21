package com.dinosaurpark.simulation;

import com.dinosaurpark.config.ParkConfig;
import com.dinosaurpark.event.*;
import com.dinosaurpark.factory.CarnivoreFactory;
import com.dinosaurpark.factory.HerbivoroFactory;
import com.dinosaurpark.model.Dinosaurio;
import com.dinosaurpark.model.TipoTicket;
import com.dinosaurpark.model.Turista;
import com.dinosaurpark.monitoring.MonitorParque;
import com.dinosaurpark.persistence.GestorJSON;
import com.dinosaurpark.persistence.RegistroEvento;
import com.dinosaurpark.persistence.RegistroGasto;
import com.dinosaurpark.persistence.RegistroIngreso;
import com.dinosaurpark.zone.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulacion {

    private final ParkConfig config;
    private final GestorJSON gestorJSON;
    private final Random random;

    private final ZonaArribo zonaArribo;
    private final RecintoCentral recintoCentral;
    private final ZonaBanos zonaBanos;
    private final PlantaEnergia plantaEnergia;
    private final List<ZonaObservacion> zonasObservacion;
    private final MonitorParque monitor;

    private final List<Turista> turistas;
    private final List<Dinosaurio> dinosaurios;

    public Simulacion() {
        this.config     = ParkConfig.getInstance();
        this.gestorJSON = new GestorJSON(config.getOutputDirectory());
        this.random     = new Random(config.getSeed());

        // Inicializar zonas
        this.zonaArribo = new ZonaArribo(
                config.getInt("arrival.maxCapacity", 30),
                config.getDouble("arrival.ticketPrice", 25.0)
        );

        this.recintoCentral = new RecintoCentral(
                100,
                config.getSouvenirPrice(),
                config.getDouble("hub.souvenirPurchaseProbability", 0.4)
        );

        this.zonaBanos = new ZonaBanos(
                config.getInt("bathroom.maxCapacity", 10),
                config.getDouble("bathroom.spaPrice", 20.0),
                config.getDouble("bathroom.spaPurchaseProbability", 0.2)
        );

        this.plantaEnergia = new PlantaEnergia(
                config.getDouble("powerplant.initialEnergy", 100.0),
                config.getDouble("powerplant.consumptionPerStep", 1.5),
                config.getDouble("powerplant.failureProbability", 0.05),
                config.getDouble("powerplant.maintenanceCost", 200.0)
        );

        this.zonasObservacion = new ArrayList<>();
        this.turistas         = new ArrayList<>();
        this.dinosaurios      = new ArrayList<>();

        // Inicializar recintos de observacion
        zonasObservacion.add(new ZonaObservacion("BASIC",
                config.getInt("enclosure.basic.maxVisitors", 20),
                config.getDouble("enclosure.basic.entryFee", 10.0)));

        zonasObservacion.add(new ZonaObservacion("PREMIUM",
                config.getInt("enclosure.premium.maxVisitors", 12),
                config.getDouble("enclosure.premium.entryFee", 30.0)));

        zonasObservacion.add(new ZonaObservacion("VIP",
                config.getInt("enclosure.vip.maxVisitors", 5),
                config.getDouble("enclosure.vip.entryFee", 75.0)));

        this.monitor = new MonitorParque(zonaArribo, plantaEnergia, zonasObservacion);
    }

    public void iniciar() {
        System.out.println("\n🦕 ¡Bienvenido al Dinosaur Park!");
        System.out.println("================================\n");

        crearDinosaurios();
        crearTuristas();
        ejecutarSimulacion();
        mostrarResumenFinal();
        gestorJSON.guardarTodo();
    }

    private void crearDinosaurios() {
        CarnivoreFactory fabCarnivoro = new CarnivoreFactory();
        HerbivoroFactory fabHerbivoro = new HerbivoroFactory();

        String[] nombresCarnivoros = {
                "T-Rex", "Carnotaurus", "Espinosaurio", "Velociraptor", "Allosaurio"
        };

        String[] nombresHerbivoros = {
                "Triceratops", "Branquiosaurio", "Estegosaurio", "Anquilosaurio", "Diplodocus",
                "Parasaurolofus", "Apatosaurio", "Iguanodonte", "Maiasaura", "Titanosaurio",
                "Paquicephalosaurus", "Minmi", "Protoceratops", "Saltasaurus", "Brachiosaurus"
        };

        int id = 1;
        for (int i = 0; i < config.getCarnivores(); i++) {
            String nombre = nombresCarnivoros[i % nombresCarnivoros.length];
            Dinosaurio d = fabCarnivoro.crear(id++, nombre, random.nextInt(8) + 3);
            dinosaurios.add(d);
            zonasObservacion.get(random.nextInt(zonasObservacion.size())).agregarDinosauro(d);
        }

        for (int i = 0; i < config.getHerbivores(); i++) {
            String nombre = nombresHerbivoros[i % nombresHerbivoros.length];
            Dinosaurio d = fabHerbivoro.crear(id++, nombre, random.nextInt(4) + 1);
            dinosaurios.add(d);
            zonasObservacion.get(random.nextInt(zonasObservacion.size())).agregarDinosauro(d);
        }

        System.out.println("🦖 " + dinosaurios.size() + " dinosaurios creados.\n");
    }

    private void crearTuristas() {
        TipoTicket[] tipos = TipoTicket.values();
        for (int i = 1; i <= config.getTourists(); i++) {
            Turista t = new Turista.Builder()
                    .conId(i)
                    .conNombre("Turista-" + i)
                    .conEdad(random.nextInt(50) + 10)
                    .conTipoTicket(tipos[random.nextInt(tipos.length)])
                    .conPresupuesto(100.0 + random.nextInt(400))
                    .construir();
            turistas.add(t);
        }
        System.out.println("👥 " + turistas.size() + " turistas creados.\n");
    }

    private void ejecutarSimulacion() {
        int totalPasos = config.getTotalSteps();

        for (int paso = 1; paso <= totalPasos; paso++) {
            System.out.println("--- Paso " + paso + "/" + totalPasos + " ---");

            // Planta de energía consume por paso
            plantaEnergia.ejecutarPaso();

            // Procesar turistas en lotes
            int lote = config.getInt("simulation.arrivalBatchSize", 5);
            int inicio = (paso - 1) * lote;
            int fin    = Math.min(inicio + lote, turistas.size());

            for (int i = inicio; i < fin; i++) {
                Turista turista = turistas.get(i);
                procesarTurista(turista);
            }

            // Evento aleatorio
            if (random.nextDouble() < 0.3) {
                generarEventoAleatorio();
            }

            // Monitoreo cada 3 pasos
            if (paso % 3 == 0) {
                monitor.actualizarTuristas(Math.min(paso * lote, turistas.size()));
                monitor.mostrarEstado();
            }
        }
    }

    private void procesarTurista(Turista turista) {
        zonaArribo.procesarTurista(turista);
        gestorJSON.agregarIngreso(new RegistroIngreso(
                "Boleto " + turista.getTipoTicket(),
                turista.getDineroGastado(),
                turista.getNombre()
        ));

        recintoCentral.procesarTurista(turista);
        zonaBanos.procesarTurista(turista);

        // Visitar un recinto de observacion aleatorio
        ZonaObservacion zona = zonasObservacion.get(random.nextInt(zonasObservacion.size()));
        zona.procesarTurista(turista);
    }

    private void generarEventoAleatorio() {
        int tipo = random.nextInt(3);
        Evento evento = switch (tipo) {
            case 0 -> new EscapeDinosaurio(
                    dinosaurios.get(random.nextInt(dinosaurios.size())),
                    turistas,   // ← pasamos la lista de turistas
                    random      // ← pasamos el random
            );
            case 1 -> new ApagonMasivo(plantaEnergia,
                    config.getDouble("powerplant.repairCost", 500.0));
            default -> new TormentaTorrencial(new ArrayList<>(zonasObservacion));
        };

        evento.resolver();
        monitor.registrarEvento();

        gestorJSON.agregarEvento(new RegistroEvento(evento));
        gestorJSON.agregarGasto(new RegistroGasto(
                "Evento: " + evento.getTipo(),
                500.0,
                "Parque General"
        ));
    }

    private void mostrarResumenFinal() {
        monitor.actualizarTuristas(turistas.size());
        monitor.mostrarEstado();
        System.out.println("\n✅ Simulación completada.");
        System.out.println("💾 Guardando datos en output/...");
    }
}