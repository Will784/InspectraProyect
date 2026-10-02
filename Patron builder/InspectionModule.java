public enum InspectionModule {
    CAMERA {
        public InspectionMission attachTo(InspectionMission mission) {
            return new CameraInspectionDecorator(mission);
        }
    },
    THERMAL {
        public InspectionMission attachTo(InspectionMission mission) {
            return new ThermalInspectionDecorator(mission);
        }
    },
    VIBRATION {
        public InspectionMission attachTo(InspectionMission mission) {
            return new VibrationAnalysisDecorator(mission);
        }
    },
    AI_ANOMALY {
        public InspectionMission attachTo(InspectionMission mission) {
            return new AIAnomalyDecorator(mission);
        }
    },
    COMPLIANCE_REPORT {
        public InspectionMission attachTo(InspectionMission mission) {
            return new ComplianceReportDecorator(mission);
        }
    };

    public abstract InspectionMission attachTo(InspectionMission mission);
}
