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

    public static String getRangesArrayString(Range[] ranges) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < ranges.length; i++) {
            stringBuilder.append(ranges[i]);

            if (i < ranges.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        double maxFrom = Math.max(from, range.from);
        double minTo = Math.min(to, range.to);

        if (maxFrom < minTo) {
            return new Range(maxFrom, minTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from < range.from && to > range.to) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)
            };
        }

        double maxFrom = Math.max(from, range.from);
        double minTo = Math.min(to, range.to);

        if (maxFrom >= minTo) {
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