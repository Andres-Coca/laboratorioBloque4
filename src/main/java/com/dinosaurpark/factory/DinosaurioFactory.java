package com.dinosaurpark.factory;

import com.dinosaurpark.model.Dinosaurio;

public interface DinosaurioFactory {
    Dinosaurio crear(int id, String nombre, int nivelPeligro);
}