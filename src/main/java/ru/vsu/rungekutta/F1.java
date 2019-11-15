package ru.vsu.rungekutta;

public class F1 implements Function {
    @Override
    public double solve(Double t, Double... vars) {
        return vars[0] + Math.exp(t);
    }

    public double exactValue(Double t, Double vars) {
        return Math.exp(t) * t;
    }
}
