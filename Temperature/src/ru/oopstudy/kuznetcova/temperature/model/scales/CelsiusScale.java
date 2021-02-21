package ru.oopstudy.kuznetcova.temperature.model.scales;

public class CelsiusScale implements TemperatureScale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Цельсия, °C";
    }
}