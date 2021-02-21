package ru.oopstudy.kuznetcova.temperature.model.scales;

public interface TemperatureScale {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}