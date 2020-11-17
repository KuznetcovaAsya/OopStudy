package ru.oopstudy.kuznetcova;

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

    public static void print(Range[] range) {
        if (range.length == 0) {
            System.out.print("[]");
        } else if (range.length == 1) {
            System.out.print("[(" + range[0].from + "; " + range[0].to + ")]");
        } else {
            System.out.print("[(" + range[0].from + "; " + range[0].to + "), " +
                    "(" + range[1].from + "; " + range[1].to + ")]");
        }
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (Math.max(from, range.from) < Math.min(to, range.to)) {
            double intersectionFrom = Math.max(from, range.from);
            double intersectionTo = Math.min(to, range.to);
            return new Range(intersectionFrom, intersectionTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{
                new Range(Math.min(from, range.from), Math.min(to, range.to)),
                new Range(Math.max(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)};
        }

        if ((from > range.from && from > range.to) || (from < range.from && to < range.from)) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range.from && to > range.to && from < range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (to > range.from && to <= range.to && from < range.from) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{};
    }

    @Override
    public String toString() {
        return "[(" + from + "; " + to + ")]";
    }
}