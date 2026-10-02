public import javax.swing.*;
import java.awt.*;

public class InspectionFrame extends JFrame {
    private final JComboBox<String> infrastructureBox =
            new JComboBox<>(new String[]{"Bridge", "Tunnel", "Industrial Structure"});
    private final JCheckBox camera = new JCheckBox("Camera Inspection");
    private final JCheckBox thermal = new JCheckBox("Thermal Analysis");
    private final JCheckBox vibration = new JCheckBox("Vibration Analysis");
    private final JCheckBox ai = new JCheckBox("AI Anomaly Detection");
    private final JCheckBox compliance = new JCheckBox("Compliance Report");
    private final JLabel pipelineLabel = new JLabel(" ");
    private final JTextArea output = new JTextArea();

    public InspectionFrame() {
        super("Robot Infrastructure Inspection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createTitledBorder("Build your mission"));
        left.add(new JLabel("Infrastructure:"));
        left.add(infrastructureBox);
        left.add(Box.createVerticalStrut(10));
        left.add(camera);
        left.add(thermal);
        left.add(vibration);
        left.add(ai);
        left.add(compliance);
        left.add(Box.createVerticalStrut(10));

        JButton run = new JButton("Run Mission");
        run.addActionListener(e -> runMission());
        left.add(run);

        output.setEditable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        add(left, BorderLayout.WEST);
        add(new JScrollPane(output), BorderLayout.CENTER);
        add(pipelineLabel, BorderLayout.NORTH);
    }

    private void runMission() {
        InspectionMission mission = new BasicInspectionMission((String) infrastructureBox.getSelectedItem());

        if (camera.isSelected()) mission = new CameraInspectionDecorator(mission);
        if (thermal.isSelected()) mission = new ThermalInspectionDecorator(mission);
        if (vibration.isSelected()) mission = new VibrationAnalysisDecorator(mission);
        if (ai.isSelected()) mission = new AIAnomalyDecorator(mission);
        if (compliance.isSelected()) mission = new ComplianceReportDecorator(mission);

        pipelineLabel.setText(" " + mission.getName());

        StringBuilder text = new StringBuilder();
        for (String line : mission.execute()) {
            text.append(line).append("\n");
        }
        output.setText(text.toString());
    }
} {
    
}

// Script realizado por: William Cando
