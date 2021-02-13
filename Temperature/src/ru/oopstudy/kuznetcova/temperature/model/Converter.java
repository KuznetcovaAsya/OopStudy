package ru.oopstudy.kuznetcova.temperature.model;

public class Converter {
    public double convertToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    public double convertToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 9 / 5 + 32;
    }
}