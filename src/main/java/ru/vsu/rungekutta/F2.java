package ru.vsu.rungekutta;

public class F2 implements Function {
    @Override
    public double solve(Double t, Double... vars) {
        return vars[0] + vars[1] - Math.exp(t);
    }

    public double exactValue(Double t, Double vars) {
        return Math.exp(t) * (0.5 * t * t - t);
    }
}
