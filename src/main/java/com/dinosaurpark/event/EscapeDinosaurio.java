package com.dinosaurpark.event;

import com.dinosaurpark.model.Carnivoro;
import com.dinosaurpark.model.Dinosaurio;
import com.dinosaurpark.model.DinosaurioEstado;
import com.dinosaurpark.model.Turista;

import java.util.List;
import java.util.Random;

public class EscapeDinosaurio extends Evento {

    private final Dinosaurio dinosaurio;
    private final List<Turista> turistas;
    private final Random random;

    public EscapeDinosaurio(Dinosaurio dinosaurio, List<Turista> turistas, Random random) {
        super(TipoEvento.ESCAPE_DINOSAURIO,
                "¡" + dinosaurio.getNombre() + " se ha escapado del recinto!");
        this.dinosaurio = dinosaurio;
        this.turistas   = turistas;
        this.random     = random;
    }

    @Override
    public void resolver() {
        System.out.println("\n🚨 ¡ALERTA! " + descripcion);

        // Solo los carnívoros atacan
        if (dinosaurio instanceof Carnivoro carnivoro) {
            System.out.println("   💀 ¡" + carnivoro.getNombre() +
                    " es carnívoro! ¡Peligro máximo!");
            intentarAtaque(carnivoro);
        } else {
            System.out.println("   🌿 " + dinosaurio.getNombre() +
                    " es herbívoro, solo está asustado.");
        }

        System.out.println("   🔒 Equipos de seguridad capturando a " +
                dinosaurio.getNombre() + "...");
        dinosaurio.setEstado(DinosaurioEstado.CONTENIDO);
        resuelto = true;
        System.out.println("   ✅ " + dinosaurio.getNombre() +
                " fue capturado y contenido.\n");
    }

    private void intentarAtaque(Carnivoro carnivoro) {
        if (turistas.isEmpty()) return;

        // El carnívoro intenta atacar entre 1 y 3 turistas
        int intentos = random.nextInt(3) + 1;
        int atacados = 0;

        for (int i = 0; i < intentos; i++) {
            Turista victima = turistas.get(random.nextInt(turistas.size()));

            // 60% de probabilidad de ataque exitoso
            if (random.nextDouble() < 0.6 && !victima.isHerido()) {
                victima.herir();
                atacados++;
                System.out.println("   🦖 " + carnivoro.getNombre() +
                        " atacó a " + victima.getNombre() + "! " +
                        "(Poder de ataque: " + carnivoro.getPoderAtaque() + ")");
            } else {
                System.out.println("   💨 " + victima.getNombre() +
                        " logró escapar de " + carnivoro.getNombre() + "!");
            }
        }

        if (atacados == 0) {
            System.out.println("   😅 ¡Nadie fue herido esta vez!");
        } else {
            System.out.println("   🚑 Servicios médicos atendiendo " +
                    atacados + " herido(s).");
        }
    }
}