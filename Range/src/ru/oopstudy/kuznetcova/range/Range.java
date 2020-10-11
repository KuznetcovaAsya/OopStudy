package ru.oopstudy.kuznetcova.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public void print() {
        System.out.printf("Диапазон от %3.2f до %3.2f%n", from, to);
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    Range intersection(double fromAnother, double toAnother) {
        if (this.from >= fromAnother && this.to <= toAnother) {
            return new Range(this.from, this.to);
        }

        if (this.from <= fromAnother && this.to >= toAnother) {
            return new Range(fromAnother, toAnother);
        }

        return null;
    }
}