package ru.vsu.rungekutta.system1;

import ru.vsu.rungekutta.Function;

public class F1 implements Function {
    @Override
    public double solve(double t, double[] x) {
        return x[0] + Math.exp(t);
    }

    @Override
    public double exactValue(double t){
        return 1 + Math.exp(t) * t;
    }
}
