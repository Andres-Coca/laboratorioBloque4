package com.dinosaurpark.zone;

import com.dinosaurpark.model.Turista;
import com.dinosaurpark.strategy.PrecioEstandar;
import com.dinosaurpark.strategy.PrecioPremium;
import com.dinosaurpark.strategy.PrecioStrategy;
import com.dinosaurpark.strategy.PrecioVIP;
import com.dinosaurpark.model.TipoTicket;

public class ZonaArribo extends Zona {

    private double totalRecaudado;
    private final double precioBase;

    public ZonaArribo(int capacidadMaxima, double precioBase) {
        super("Zona de Arribo", capacidadMaxima);
        this.precioBase = precioBase;
        this.totalRecaudado = 0.0;
    }

    @Override
    public void procesarTurista(Turista turista) {
        // Aquí usamos el patrón Strategy para calcular el precio
        PrecioStrategy estrategia = obtenerEstrategia(turista.getTipoTicket());
        double precio = estrategia.calcularPrecio(turista);

        turista.gastar(precio);
        totalRecaudado += precio;

        System.out.println("🎫 " + turista.getNombre() +
                " compró boleto " + turista.getTipoTicket() +
                " por $" + precio);
    }

    private PrecioStrategy obtenerEstrategia(TipoTicket tipo) {
        return switch (tipo) {
            case PREMIUM -> new PrecioPremium(precioBase);
            case VIP     -> new PrecioVIP(precioBase);
            default      -> new PrecioEstandar(precioBase);
        };
    }

    public double getTotalRecaudado() { return totalRecaudado; }
}