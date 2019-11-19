package ru.vsu.rungekutta.system1;

import ru.vsu.rungekutta.Function;

public class F2 implements Function {
    @Override
    public double solve(double t, double[] x) {
        return x[0] + x[1] - Math.exp(t);
    }

    @Override
    public double exactValue(double t){
        return Math.exp(t) * (0.5 * t * t - t);
    }
}
