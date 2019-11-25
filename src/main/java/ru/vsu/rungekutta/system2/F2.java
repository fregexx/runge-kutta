package ru.vsu.rungekutta.system2;

import ru.vsu.rungekutta.Function;

public class F2 implements Function {
    @Override
    public double solve(double t, double[] x) {
        return -x[0] + 3 * x[1];
    }

//    @Override
//    public double exactValue(double t) {
//        return Math.exp(-t) - Math.exp(2 * t);
//    }
}
