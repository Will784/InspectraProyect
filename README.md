# Robot Infrastructure Inspection System

A Java application that uses the **Decorator pattern** to build robot inspection missions for bridges, tunnels and industrial structures. The user builds a mission by selecting inspection modules in a Swing interface.

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

## Grupo de Trabajo:

William Eduardo Cando Cuarán
Victor Manuel Aguilar Agredo
Samuel Santiago Hurtado Argoti
