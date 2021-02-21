package ru.oopstudy.kuznetcova.temperature.model;

import ru.oopstudy.kuznetcova.temperature.model.scales.TemperatureScale;

public interface TemperatureConverter {
    TemperatureScale[] getTemperatureScales();

    double convert(double temperature, TemperatureScale fromScale, TemperatureScale toScale);
}