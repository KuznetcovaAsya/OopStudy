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

    public static void print(Range[] range) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < range.length; i++) {
            stringBuilder.append(range[i]);

            if (i < range.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        System.out.print(stringBuilder.toString());
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range == null) {
            return null;
        }

        if (Math.max(from, range.from) < Math.min(to, range.to)) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range == null) {
            return new Range[]{new Range(from, to)};
        }

        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (range == null) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)
            };
        }

        if (getIntersection(range) == null) {
            return new Range[]{new Range(from, to)};
        }

        if (to > range.from && from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (from < range.to && from >= range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}