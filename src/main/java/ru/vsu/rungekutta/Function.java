package ru.vsu.rungekutta;

@FunctionalInterface
public interface Function {
    public double apply(double t, double[] x);
}
