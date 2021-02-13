package ru.oopstudy.kuznetcova.temperature.view;

import ru.oopstudy.kuznetcova.temperature.model.Converter;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements View {
    private final Converter temperatureConverter;

    public DesktopView(Converter converter) {
        this.temperatureConverter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(550, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setLayout(new GridLayout(4, 2));

            JLabel celsiusLabel = new JLabel("Введите температуру в градусах Цельсия");
            frame.add(celsiusLabel);

            JTextField celsiusTemperatureField = new JTextField(10);
            frame.add(celsiusTemperatureField);

            JLabel kelvinLabel = new JLabel("Температура в градусах Кельвина");
            frame.add(kelvinLabel);
            JLabel kelvinTemperatureLabel = new JLabel();
            frame.add(kelvinTemperatureLabel);

            JLabel fahrenheitLabel = new JLabel("Температура в градусах Фаренгейта");
            frame.add(fahrenheitLabel);
            JLabel fahrenheitTemperatureLabel = new JLabel();
            frame.add(fahrenheitTemperatureLabel);

            JButton convertButton = new JButton("Перевести температуру");
            convertButton.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(celsiusTemperatureField.getText());

                    kelvinTemperatureLabel.setText(Double.toString(temperatureConverter.convertToKelvin(celsiusTemperature)));
                    fahrenheitTemperatureLabel.setText(Double.toString(temperatureConverter.convertToFahrenheit(celsiusTemperature)));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом");
                }
            });

            frame.add(convertButton);

            frame.setVisible(true);
        });
    }
}