# 🦕 Dinosaur Park — Simulación

Simulación de un parque temático de dinosaurios con comportamiento dinámico, eventos aleatorios y persistencia de datos en Java 17 con Maven.

---

## 🛠️ Herramientas utilizadas

| Herramienta | Versión |
|---|---|
| Java (Eclipse Adoptium) | 17 |
| Maven | 3.9+ |
| Jackson Databind | 2.15.2 |
| JUnit Jupiter | 5.10.0 |
| Mockito | 5.5.0 |
| JaCoCo | 0.8.10 |
| IntelliJ IDEA | 2026.1 |

> No se utiliza base de datos. La persistencia se realiza mediante archivos JSON y CSV.

---

## ⚙️ Instrucciones de configuración

### 1. Clonar o descargar el proyecto

### 2. Configurar `park.properties`
El archivo se encuentra en `src/main/resources/park.properties`.
Puedes modificar los valores antes de ejecutar:

```properties
tourists=50                        # Número de turistas
dinosaurs.carnivores=5             # Carnívoros en el parque
dinosaurs.herbivores=15            # Herbívoros en el parque
simulation.seed=42                 # Semilla (mismo valor = mismos resultados)
simulation.totalSteps=100          # Pasos de la simulación
arrival.ticketPrice=25.0           # Precio base del boleto
output.directory=output            # Carpeta donde se guardan los archivos
```

### 3. Compilar el proyecto
```bash
mvn compile
```

---

## ▶️ Forma de ejecución

### Opción 1 — Desde IntelliJ
Abrir `Main.java` y presionar ▶️

### Opción 2 — Desde terminal
```bash
mvn exec:java
```

### Opción 3 — Correr pruebas
```bash
mvn test
```

### Opción 4 — Ver cobertura
```bash
mvn test
# Luego abrir: target/site/jacoco/index.html
```

---

## 📋 Explicación general del sistema

El sistema simula la operación de un parque temático de dinosaurios durante N pasos de simulación. En cada paso:

1. **Los turistas llegan** en lotes a la Zona de Arribo y compran su boleto
   2. **Visitan el Recinto Central** donde pueden comprar souvenirs
   3. **Usan los baños** con posibilidad de usar el SPA
   4. **Observan dinosaurios** en recintos BASIC, PREMIUM o VIP
   5. **La planta de energía** consume energía en cada paso
   6. **Pueden ocurrir eventos aleatorios** como escapes, apagones o tormentas

Al finalizar, el sistema genera 6 archivos en la carpeta `output/`:

    output/
    ├── ingresos.json → ventas de boletos y souvenirs
    ├── gastos.json      → costos operativos y eventos
    ├── eventos.json     → registro de todos los eventos
    ├── revenues.csv     → ingresos en formato CSV
    ├── expenses.csv     → gastos en formato CSV
    └── events.csv       → eventos en formato CSV


### Zonas del parque

| Zona | Descripción |
|---|---|
| Zona de Arribo | Entrada del parque, venta de boletos |
| Recinto Central | Venta de souvenirs |
| Baños | Descanso y SPA opcional |
| Zona de Observación | Recintos BASIC, PREMIUM y VIP |
| Planta de Energía | Suministro eléctrico del parque |

### Eventos aleatorios

| Evento | Efecto |
|---|---|
| 🚨 Escape de dinosaurio | Los carnívoros pueden atacar turistas |
| 🔴 Apagón masivo | La planta se repara con costo |
| 🌧️ Tormenta torrencial | Las zonas cierran temporalmente |

---

## 🎨 Patrones de diseño utilizados

### 1. Singleton — `ParkConfig`
**Ubicación:** `config/ParkConfig.java`

**¿Por qué?** La configuración del parque debe leerse una sola vez desde `park.properties`. Con Singleton garantizamos que todas las clases comparten la misma instancia.

Flujo del Singleton (ParkConfig):

1. Se llama a `ParkConfig.getInstance()`
   2. Se verifica si la instancia ya existe:
       - Si NO existe → se crea una nueva instancia
       - Si YA existe → se devuelve la instancia existente

```java
// Constructor privado — nadie puede hacer new ParkConfig()
private ParkConfig() { }

// Punto de acceso global
public static ParkConfig getInstance() {
    if (instance == null) instance = new ParkConfig();
    return instance;
}
```

---

### 2. Abstract Factory — `DinosaurioFactory`
**Ubicación:** `factory/`

**¿Por qué?** Necesitamos crear dos familias de dinosaurios (carnívoros y herbívoros) sin que el código principal sepa los detalles de construcción.

    DinosaurioFactory (interfaz)
    ├── CarnivoreFactory → crea Carnivoro
    └── HerbivoroFactory → crea Herbivoro

```java
DinosaurioFactory fabrica = new CarnivoreFactory();
Dinosaurio rex = fabrica.crear(1, "Rex", 8);
```

---

### 3. Builder — `Turista.Builder`
**Ubicación:** `model/Turista.java`

**¿Por qué?** Los turistas tienen muchos parámetros opcionales. Builder permite crearlos de forma legible y flexible.

```java
Turista t = new Turista.Builder()
    .conId(1)
    .conNombre("Juan")
    .conTipoTicket(TipoTicket.VIP)
    .conPresupuesto(500.0)
    .construir();
```

---

### 4. Strategy — `PrecioStrategy`
**Ubicación:** `strategy/`

**¿Por qué?** El precio del boleto varía según el tipo de ticket. Strategy permite cambiar el algoritmo de precio sin modificar el código existente.

    PrecioStrategy (interfaz)
    ├── PrecioEstandar → precio base
    ├── PrecioPremium  → precio base × 1.5
    └── PrecioVIP      → precio base × 3.0

```java
PrecioStrategy estrategia = new PrecioPremium(25.0);
double precio = estrategia.calcularPrecio(turista); // → 37.5
```

---

## 🧪 Pruebas unitarias

- **58 pruebas** distribuidas en 8 clases de test
  - **Cobertura JaCoCo: 57%** (mínimo requerido: 45%)

| Clase de prueba | Pruebas |
|---|---|
| TuristaTest | 4 |
| DinosaurioTest | 9 |
| ParkConfigTest | 7 |
| ZonaArriboTest | 7 |
| PlantaEnergiaTest | 7 |
| EventoTest | 7 |
| PersistenciaTest | 9 |
| ZonasTest | 8 |

---

## 📁 Estructura del proyecto

    DinosaurPark/
    ├── pom.xml
    ├── README.md
    └── src/
    ├── main/
    │   ├── java/com/dinosaurpark/
    │   │   ├── Main.java
    │   │   ├── config/
    │   │   ├── event/
    │   │   ├── factory/
    │   │   ├── model/
    │   │   ├── monitoring/
    │   │   ├── persistence/
    │   │   ├── simulation/
    │   │   ├── strategy/
    │   │   └── zone/
    │   └── resources/
    │       └── park.properties
    └── test/
        └── java/com/dinosaurpark/