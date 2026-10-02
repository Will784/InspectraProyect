# Task: patrones creacionales en Inspectra

## Objetivo

Implementar en este proyecto los patrones **Prototype**, **Builder** y **Abstract Factory**, integrados con el dominio existente de misiones de inspección (patrón Decorator), y subir los cambios a GitHub.

Repositorio: https://github.com/Will784/InspectraProyect (rama `main`)

## Tareas

- [x] Implementar el patrón Builder
- [x] Implementar el patrón Prototype
- [x] Implementar el patrón Abstract Factory
- [x] Agregar una demo de consola que ejercite los tres patrones
- [x] Documentar los patrones en el README
- [x] Corregir los errores de compilación que ya tenía el proyecto
- [x] Actualizar las instrucciones de compilación y ejecución del README
- [x] Agregar `.gitignore` para la salida de compilación
- [x] Subir los cambios a GitHub

## Cambios realizados

Los tres patrones producen la misma `InspectionMission` decorada que ya existía, por lo que se componen con el Decorator.

### Builder (`Patron builder/`)

| Archivo | Rol |
|---|---|
| `MissionBuilder.java` | Interfaz del builder |
| `InspectionMissionBuilder.java` | Builder concreto: parte de `BasicInspectionMission` y envuelve los decoradores |
| `InspectionModule.java` | Enum con una constante por decorador (pasos de construcción) |
| `MissionDirector.java` | Director con tres recetas: `buildQuickCheck`, `buildStructuralSurvey`, `buildFullAudit` |

El builder aplica los módulos siempre en el mismo orden fijo, sin importar el orden en que se agreguen.

### Prototype (`Patron prototype/`)

| Archivo | Rol |
|---|---|
| `MissionPrototype.java` | Interfaz del prototipo, declara `copy()` |
| `MissionProfile.java` | Prototipo concreto (infraestructura, zonas y módulos) con copia profunda |
| `MissionProfileRegistry.java` | Registro de plantillas por clave; siempre entrega clones |

`MissionProfile.toMission(MissionBuilder)` convierte el perfil clonado en una misión decorada usando el Builder.

### Abstract Factory (`Patron abstract factory/`)

| Archivo | Rol |
|---|---|
| `InspectionKitFactory.java` | Fábrica abstracta: crea robot, sensor y misión compatibles |
| `BridgeInspectionFactory.java`, `TunnelInspectionFactory.java`, `IndustrialInspectionFactory.java` | Fábricas concretas, una por infraestructura |
| `InspectionRobot.java` | Producto abstracto: robot |
| `AerialDroneRobot.java`, `CrawlerRobot.java`, `ClimbingRobot.java` | Robots concretos |
| `InspectionSensor.java` | Producto abstracto: sensor |
| `LidarSensor.java`, `GasSensor.java`, `UltrasonicSensor.java` | Sensores concretos |

| Fábrica | Robot | Sensor | Módulos de la misión |
|---|---|---|---|
| `BridgeInspectionFactory` | `AerialDroneRobot` | `LidarSensor` | Camera, Vibration |
| `TunnelInspectionFactory` | `CrawlerRobot` | `GasSensor` | Camera, Thermal |
| `IndustrialInspectionFactory` | `ClimbingRobot` | `UltrasonicSensor` | Thermal, Vibration, AI Anomaly |

### Otros cambios

| Archivo | Cambio |
|---|---|
| `CreationalPatternsDemo.java` | Nuevo: demo de consola de los tres patrones |
| `README.md` | Sección "Creational patterns", lista de archivos e instrucciones de compilación y ejecución |
| `InspectionFrame.java` | Corregido `public import` inválido y llaves sobrantes al final |
| `ComplianceReportDecorator.java` | Corregido `public import` inválido y llaves sobrantes al final |
| `.gitignore` | Nuevo: excluye `out/` y `*.class` |

## Commits

| Commit | Descripción |
|---|---|
| `c82089e` | Add Prototype, Builder and Abstract Factory patterns |
| `8238d02` | Fix stray braces in ComplianceReportDecorator |
| `12d1063` | Update run instructions in README.md |
| `ee6a97c` | Add .gitignore for build output |

## Verificación

Desde la raíz del proyecto:

```
javac -d out *.java "Patron decorator"/*.java "Patron builder"/*.java "Patron prototype"/*.java "Patron abstract factory"/*.java
java -cp out CreationalPatternsDemo
java -cp out Main
```

- El proyecto completo compila sin errores.
- `CreationalPatternsDemo` se ejecuta y muestra la salida de los tres patrones.
- La interfaz Swing (`Main`) compila, pero no se probó abriendo la ventana.
