package ru.vsu.rungekutta;

public interface Function {
    public double solve(double t, double[] x);
    public double exactValue(double t);
}
