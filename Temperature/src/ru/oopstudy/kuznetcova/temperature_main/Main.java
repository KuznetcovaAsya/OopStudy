package ru.oopstudy.kuznetcova.temperature_main;

import ru.oopstudy.kuznetcova.temperature.model.Converter;
import ru.oopstudy.kuznetcova.temperature.view.DesktopView;
import ru.oopstudy.kuznetcova.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Converter temperatureConverter = new Converter();
        View view = new DesktopView(temperatureConverter);

        view.start();
    }
}