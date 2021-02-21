package ru.oopstudy.kuznetcova.temperature.view;

import ru.oopstudy.kuznetcova.temperature.model.TemperatureConverter;
import ru.oopstudy.kuznetcova.temperature.model.scales.TemperatureScale;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DesktopView implements View {
    private final TemperatureConverter converter;

    public DesktopView(TemperatureConverter converter) {
        this.converter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            JPanel panel = new JPanel(new GridBagLayout());
            frame.add(panel);

            JLabel title = new JLabel("Введите температуру:");

            title.setBorder(new EmptyBorder(0, 10, 10, 10));
            title.setFont(new Font("Arial", Font.PLAIN, 20));

            GridBagConstraints constraints = new GridBagConstraints();

            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = 0;
            constraints.gridwidth = 2;
            panel.add(title, constraints);

            JTextField fromTextField = new JFormattedTextField();

            fromTextField.setPreferredSize(new Dimension(150, 50));
            fromTextField.setFont(new Font("Arial", Font.PLAIN, 30));
            fromTextField.setMargin(new Insets(0, 10, 0, 10));

            JLabel toLabel = new JLabel();

            toLabel.setPreferredSize(new Dimension(150, 50));
            toLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            toLabel.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1),
                    new EmptyBorder(0, 10, 0, 10)));
            toLabel.setOpaque(true);
            toLabel.setBackground(Color.WHITE);

            constraints.gridwidth = 1;
            constraints.gridy = 1;
            constraints.insets.set(0, 7, 0, 7);
            panel.add(fromTextField, constraints);

            constraints.gridx = 1;
            panel.add(toLabel, constraints);

            JComboBox<TemperatureScale> fromComboBox = new JComboBox<>(converter.getTemperatureScales());
            fromComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

            JComboBox<TemperatureScale> toComboBox = new JComboBox<>(converter.getTemperatureScales());
            toComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(fromComboBox, constraints);

            constraints.gridx = 1;
            panel.add(toComboBox, constraints);

            JButton convertButton = new JButton("Перевести температуру");
            convertButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(fromTextField.getText());

                    TemperatureScale fromScale = fromComboBox.getItemAt(fromComboBox.getSelectedIndex());
                    TemperatureScale toScale = toComboBox.getItemAt(toComboBox.getSelectedIndex());

                    toLabel.setText(Double.toString(converter.convert(temperature, fromScale, toScale)));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            convertButton.setPreferredSize(new Dimension(200, 50));
            convertButton.setFont(new Font("Arial", Font.PLAIN, 20));

            constraints.insets.set(10, 0, 0, 0);
            constraints.gridwidth = 2;
            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(convertButton, constraints);

            frame.setVisible(true);
        });
    }
}