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
        System.out.println("(" + from + "; " + to + ")");
    }

    public void printArray(Range[] range) {
        for (Range value : range) {
            System.out.print("(" + value.getFrom() + "; " + value.getTo() + ")");
        }
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range anotherRange) {
        if (Math.max(from, anotherRange.from) < Math.min(to, anotherRange.to)) {
            return new Range(Math.max(from, anotherRange.from), Math.min(to, anotherRange.to));
        }

        return null;
    }

    public Range[] getMerging(Range anotherRange) {
        if (Math.max(from, anotherRange.from) <= Math.min(to, anotherRange.to)) {
            Range[] mergingRange = new Range[1];
            mergingRange[0] = new Range(Math.min(from, anotherRange.from), Math.max(to, anotherRange.to));
            return mergingRange;
        }

        Range[] mergingRange = new Range[2];
        mergingRange[0] = new Range(Math.min(from, anotherRange.from), Math.min(to, anotherRange.to));
        mergingRange[1] = new Range(Math.max(from, anotherRange.from), Math.max(to, anotherRange.to));
        return mergingRange;
    }

    public Range[] getRemainder(Range anotherRange) {
        if (from < anotherRange.from && to > anotherRange.to) {
            Range[] remainderRange = new Range[2];
            remainderRange[0] = new Range(from, anotherRange.from);
            remainderRange[1] = new Range(anotherRange.to, to);
            return remainderRange;
        }

        if (from >= anotherRange.from && to > anotherRange.to) {
            Range[] remainderRange = new Range[1];
            remainderRange[0] = new Range(anotherRange.to, to);
            return remainderRange;
        }

        if (anotherRange.from > from && anotherRange.to >= to) {
            Range[] remainderRange = new Range[1];
            remainderRange[0] = new Range(from, anotherRange.from);
            return remainderRange;
        }

        return null;
    }
}