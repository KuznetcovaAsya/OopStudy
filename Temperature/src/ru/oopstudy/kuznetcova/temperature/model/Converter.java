package ru.oopstudy.kuznetcova.temperature.model;

import ru.oopstudy.kuznetcova.temperature.model.scales.TemperatureScale;

public class Converter implements TemperatureConverter {
    private final TemperatureScale[] scales;

    public Converter(TemperatureScale[] scales) {
        this.scales = scales;
    }

    public TemperatureScale[] getTemperatureScales() {
        return scales;
    }

    public double convert(double temperature, TemperatureScale fromScale, TemperatureScale toScale) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(temperature));
    }
}