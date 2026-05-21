package com.dinosaurpark.event;

import com.dinosaurpark.model.Dinosaurio;
import com.dinosaurpark.model.DinosaurioEstado;

public class EscapeDinosaurio extends Evento {

    private final Dinosaurio dinosaurio;

    public EscapeDinosaurio(Dinosaurio dinosaurio) {
        super(TipoEvento.ESCAPE_DINOSAURIO,
                "¡" + dinosaurio.getNombre() + " se ha escapado del recinto!");
        this.dinosaurio = dinosaurio;
    }

    @Override
    public void resolver() {
        System.out.println("🚨 ¡ALERTA! " + descripcion);
        System.out.println("   🔒 Equipos de seguridad capturando a " + dinosaurio.getNombre() + "...");
        dinosaurio.setEstado(DinosaurioEstado.CONTENIDO);
        resuelto = true;
        System.out.println("   ✅ " + dinosaurio.getNombre() + " fue capturado y contenido.");
    }

    public Dinosaurio getDinosaurio() { return dinosaurio; }
}