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
        System.out.print("(" + from + "; " + to + ")");
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range anotherRange) {
        /*if (this.from >= anotherRange.from && this.to <= anotherRange.to) {
            return new Range(this.from, this.to);
        }

        if (this.from <= anotherRange.from && this.to >= anotherRange.to) {
            return new Range(anotherRange.from, anotherRange.to);
        }*/



        return null;
    }
}