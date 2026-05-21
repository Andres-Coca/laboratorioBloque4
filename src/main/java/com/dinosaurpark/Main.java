package com.dinosaurpark;

import com.dinosaurpark.model.TipoTicket;
import com.dinosaurpark.model.Turista;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {

            System.out.println("\n=================================");
            System.out.println("       DINOSAUR PARK");
            System.out.println("=================================");
            System.out.println("1. Registrar dinosaurio");
            System.out.println("2. Registrar turista");
            System.out.println("3. Comprar boleto");
            System.out.println("4. Simular evento");
            System.out.println("5. Ver estado del parque");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion: ");

            option = scanner.nextInt();

            switch (option) {

                case 1:
                    System.out.println("Registrando dinosaurio...");
                    break;

                case 2:
                    Turista turista = new Turista.Builder()
                            .conId(1)
                            .conNombre("Juan")
                            .conEdad(25)
                            .conTipoTicket(TipoTicket.PREMIUM)
                            .conPresupuesto(500.0)
                            .construir();
                    turista.mostrarInformacion();
                    break;

                case 3:
                    System.out.println("Comprando boleto...");
                    break;

                case 4:
                    System.out.println("Simulando evento...");
                    break;

                case 5:
                    System.out.println("Mostrando estado del parque...");
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida");

            }

        } while (option != 6);

        scanner.close();
    }
}