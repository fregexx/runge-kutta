package ru.vsu.rungekutta.system2;

import ru.vsu.rungekutta.Function;

public class F1 implements Function {
    @Override
    public double solve(double t, double[] x) {
        return -2 * x[0] + 4 * x[1];
    }

    @Override
    public double exactValue(double t) {
        return 4 * Math.exp(-t) - Math.exp(2 * t);
    }
}
