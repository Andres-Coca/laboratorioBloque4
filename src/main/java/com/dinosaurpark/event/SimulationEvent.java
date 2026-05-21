package com.dinosaurpark.event;

public interface SimulationEvent {
    void resolver();
    String getDescripcion();
    boolean isResuelto();
}