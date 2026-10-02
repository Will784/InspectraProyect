# Robot Infrastructure Inspection System

A Java application that uses the **Decorator pattern** to build robot inspection missions for bridges, tunnels and industrial structures. The user builds a mission by selecting inspection modules in a Swing interface.

The project also applies three creational patterns on top of the same `InspectionMission` component: **Builder**, **Prototype** and **Abstract Factory**.

## Real-world case

A company inspects infrastructure using robots. Each type of infrastructure needs a different combination of inspection capabilities, so the mission must be composed dynamically instead of using a class for every possible combination.

## Pattern structure

| Role | Class |
|------|-------|
| Component | `InspectionMission` (interface) |
| Concrete component | `BasicInspectionMission` |
| Base decorator | `MissionDecorator` (abstract) |
| Concrete decorators | `CameraInspectionDecorator`, `ThermalInspectionDecorator`, `VibrationAnalysisDecorator`, `AIAnomalyDecorator`, `ComplianceReportDecorator` |

Mission flow:

```
Inspection Mission
       ↓
Camera Inspection
       ↓
Thermal Analysis
       ↓
Vibration Analysis
       ↓
AI Anomaly Detection
       ↓
Compliance Report
```

Any subset of the decorators can be applied, always on top of the basic mission.

## Creational patterns

The three creational patterns create the same decorated `InspectionMission` objects, so they work together with the Decorator instead of replacing it.

### Builder

Builds a mission step by step. The builder keeps the selected modules and always wraps the decorators in the fixed order, no matter the order in which they were added.

| Role | Class |
|------|-------|
| Builder | `MissionBuilder` (interface) |
| Concrete builder | `InspectionMissionBuilder` |
| Director | `MissionDirector` |
| Product | `InspectionMission` (decorator chain) |
| Build steps | `InspectionModule` (enum, one constant per decorator) |

```java
MissionBuilder builder = new InspectionMissionBuilder();
InspectionMission audit = new MissionDirector().buildFullAudit(builder, "Tunnel");

InspectionMission custom = builder.reset()
        .forInfrastructure("Bridge")
        .withModule(InspectionModule.THERMAL)
        .withModule(InspectionModule.COMPLIANCE_REPORT)
        .build();
```

### Prototype

Mission profiles are stored as templates and cloned every time a new mission is planned. The copy is deep (zones and modules are copied), so editing a clone never changes the template.

| Role | Class |
|------|-------|
| Prototype | `MissionPrototype` (interface) |
| Concrete prototype | `MissionProfile` |
| Prototype registry | `MissionProfileRegistry` |

```java
MissionProfileRegistry registry = new MissionProfileRegistry();
registry.register("bridge-routine", new MissionProfile("Bridge")
        .addZone("Deck")
        .addModule(InspectionModule.CAMERA));

MissionProfile custom = registry.create("bridge-routine")
        .addZone("Pillar 3")
        .addModule(InspectionModule.AI_ANOMALY);
InspectionMission mission = custom.toMission(new InspectionMissionBuilder());
```

### Abstract Factory

Each infrastructure type has its own family of compatible products: a robot, a sensor and a mission.

| Role | Class |
|------|-------|
| Abstract factory | `InspectionKitFactory` (interface) |
| Concrete factories | `BridgeInspectionFactory`, `TunnelInspectionFactory`, `IndustrialInspectionFactory` |
| Abstract products | `InspectionRobot`, `InspectionSensor`, `InspectionMission` |
| Concrete robots | `AerialDroneRobot`, `CrawlerRobot`, `ClimbingRobot` |
| Concrete sensors | `LidarSensor`, `GasSensor`, `UltrasonicSensor` |

| Factory | Robot | Sensor | Mission modules |
|---------|-------|--------|-----------------|
| `BridgeInspectionFactory` | `AerialDroneRobot` | `LidarSensor` | Camera, Vibration |
| `TunnelInspectionFactory` | `CrawlerRobot` | `GasSensor` | Camera, Thermal |
| `IndustrialInspectionFactory` | `ClimbingRobot` | `UltrasonicSensor` | Thermal, Vibration, AI Anomaly |

```java
InspectionKitFactory factory = new TunnelInspectionFactory();
InspectionRobot robot = factory.createRobot();
InspectionSensor sensor = factory.createSensor();
InspectionMission mission = factory.createMission();
```

## Project files

```
Main.java
InspectionFrame.java
InspectionMission.java
BasicInspectionMission.java
MissionDecorator.java
CameraInspectionDecorator.java
ThermalInspectionDecorator.java
VibrationAnalysisDecorator.java
AIAnomalyDecorator.java
ComplianceReportDecorator.java
CreationalPatternsDemo.java

Patron builder/
    InspectionModule.java
    MissionBuilder.java
    InspectionMissionBuilder.java
    MissionDirector.java

Patron prototype/
    MissionPrototype.java
    MissionProfile.java
    MissionProfileRegistry.java

Patron abstract factory/
    InspectionKitFactory.java
    BridgeInspectionFactory.java
    TunnelInspectionFactory.java
    IndustrialInspectionFactory.java
    InspectionRobot.java
    AerialDroneRobot.java
    CrawlerRobot.java
    ClimbingRobot.java
    InspectionSensor.java
    LidarSensor.java
    GasSensor.java
    UltrasonicSensor.java
```

## How it works

Each decorator wraps another `InspectionMission`, runs the wrapped mission first, and then adds its own result to the log:

```java
InspectionMission mission = new BasicInspectionMission("Bridge");
mission = new CameraInspectionDecorator(mission);
mission = new ThermalInspectionDecorator(mission);
mission = new ComplianceReportDecorator(mission);
mission.execute();
```

## User interface

1. Choose the infrastructure type: Bridge, Tunnel or Industrial Structure.
2. Check the inspection modules you want.
3. Click **Run Mission**.

The top of the window shows the decorator chain that was built, and the main area shows the result of each step.

## Requirements:

ID	Requerimiento
RF-01	El sistema debe permitir crear una misión de inspección base (InspectionMission) para un tipo de infraestructura.
RF-02	El usuario debe poder elegir el tipo de infraestructura: puente, túnel o estructura industrial.
RF-03	El usuario debe poder agregar a la misión el módulo de inspección visual (CameraInspectionDecorator).
RF-04	El usuario debe poder agregar el módulo de análisis térmico (ThermalInspectionDecorator).
RF-05	El usuario debe poder agregar el módulo de análisis de vibraciones (VibrationAnalysisDecorator).
RF-06	El usuario debe poder agregar el módulo de detección de anomalías con IA (AIAnomalyDecorator).
RF-07	El usuario debe poder agregar el módulo de reporte de cumplimiento (ComplianceReportDecorator).
RF-08	El sistema debe permitir cualquier combinación de módulos, incluso ninguno.
RF-09	Los módulos seleccionados deben ejecutarse en un orden fijo: cámara, térmico, vibración, IA y reporte.
RF-10	El sistema debe mostrar la cadena de la misión construida (por ejemplo: Inspection Mission -> Camera -> Thermal).
RF-11	Al ejecutar la misión, el sistema debe mostrar el resultado de cada paso en pantalla.
RF-12	El reporte de cumplimiento debe incluir la cantidad de pasos documentados en la misión.
Requerimientos no funcionales
ID	Requerimiento
RNF-01	El sistema debe estar desarrollado en Java 11 o superior.
RNF-02	Debe aplicar el patrón de diseño Decorator.
RNF-03	La interfaz gráfica debe estar hecha con Swing.
RNF-04	El código y los textos de la interfaz deben estar en inglés.
RNF-05	El código debe ser simple y no debe contener comentarios.
RNF-06	Agregar un nuevo módulo de inspección no debe requerir modificar las clases existentes.
RNF-07	El sistema debe ejecutarse sin librerías externas, solo con el JDK.
RNF-08	La interfaz debe ser clara: el usuario debe poder armar y ejecutar una misión en pocos clics.

- Java 11 or higher (JDK)

## Run

Place all files in the same folder and run:

```
javac *.java
java Main
```

`Main` opens the Swing interface (Decorator). To see the creational patterns in the console run:

```
java CreationalPatternsDemo
```

## Grupo de Trabajo:

William Eduardo Cando Cuarán
Victor Manuel Aguilar Agredo
Samuel Santiago Hurtado Argoti
