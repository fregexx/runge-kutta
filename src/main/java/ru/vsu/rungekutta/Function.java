package ru.vsu.rungekutta;

@FunctionalInterface
public interface Function {
    public double solve(double t, double[] x);
}
