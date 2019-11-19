package ru.vsu.rungekutta.system0;

import ru.vsu.rungekutta.Function;

public class F0 implements Function {

    @Override
    public double solve(double t, double[] x) {
        return t + x[0];
    }

    @Override
    public double exactValue(double t){
        return 2 * Math.exp(t) - t - 1;
    }
}