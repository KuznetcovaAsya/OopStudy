package ru.oopstudy.kuznetcova.temperature_main;

import ru.oopstudy.kuznetcova.temperature.model.Converter;
import ru.oopstudy.kuznetcova.temperature.model.TemperatureConverter;
import ru.oopstudy.kuznetcova.temperature.model.scales.CelsiusScale;
import ru.oopstudy.kuznetcova.temperature.model.scales.FahrenheitScale;
import ru.oopstudy.kuznetcova.temperature.model.scales.KelvinScale;
import ru.oopstudy.kuznetcova.temperature.model.scales.TemperatureScale;
import ru.oopstudy.kuznetcova.temperature.view.DesktopView;
import ru.oopstudy.kuznetcova.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureScale[] scales = {
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        };

        TemperatureConverter converter = new Converter(scales);
        View view = new DesktopView(converter);

        view.start();
    }
}