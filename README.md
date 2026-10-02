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

## Requirements

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
