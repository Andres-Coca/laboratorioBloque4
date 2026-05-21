package com.dinosaurpark;

import com.dinosaurpark.simulation.Simulacion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("       DINOSAUR PARK");
            System.out.println("=================================");
            System.out.println("1. Iniciar simulación");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opcion: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Simulacion simulacion = new Simulacion();
                    simulacion.iniciar();
                    break;

                case 2:
                    System.out.println("👋 ¡Hasta luego!");
                    break;

                default:
                    System.out.println("⚠️  Opción inválida");
            }

        } while (opcion != 2);

        scanner.close();
    }
}