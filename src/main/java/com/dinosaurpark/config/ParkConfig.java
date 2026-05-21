package com.dinosaurpark.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ParkConfig {

    private static ParkConfig instance;
    private final Properties props;

    // Constructor PRIVADO — nadie puede hacer "new ParkConfig()"
    private ParkConfig() {
        props = new Properties();
        try (InputStream in = getClass()
                .getClassLoader()
                .getResourceAsStream("park.properties")) {

            if (in != null) {
                props.load(in);
                System.out.println("✅ Configuración cargada desde park.properties");
            } else {
                System.out.println("⚠️  park.properties no encontrado");
            }

        } catch (IOException e) {
            System.out.println("⚠️  Error leyendo configuración: " + e.getMessage());
        }
    }

    // Punto de acceso global — crea la instancia solo si no existe
    public static ParkConfig getInstance() {
        if (instance == null) {
            instance = new ParkConfig();
        }
        return instance;
    }

    // Leer número entero
    public int getInt(String key, int defaultValue) {
        String val = props.getProperty(key);
        if (val == null) return defaultValue;
        try { return Integer.parseInt(val.trim()); }
        catch (NumberFormatException e) { return defaultValue; }
    }

    // Leer número decimal
    public double getDouble(String key, double defaultValue) {
        String val = props.getProperty(key);
        if (val == null) return defaultValue;
        try { return Double.parseDouble(val.trim()); }
        catch (NumberFormatException e) { return defaultValue; }
    }

    // Leer texto
    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    // Métodos específicos del parque
    public long   getSeed()             { return (long) getInt("simulation.seed", 42); }
    public int    getTotalSteps()       { return getInt("simulation.totalSteps", 100); }
    public int    getTourists()         { return getInt("tourists", 50); }
    public int    getCarnivores()       { return getInt("dinosaurs.carnivores", 5); }
    public int    getHerbivores()       { return getInt("dinosaurs.herbivores", 15); }
    public double getTicketPrice()      { return getDouble("arrival.ticketPrice", 25.0); }
    public double getSouvenirPrice()    { return getDouble("hub.souvenirPrice", 15.0); }
    public String getOutputDirectory()  { return getString("output.directory", "output"); }

    // Solo para tests
    static void resetForTesting() { instance = null; }
}