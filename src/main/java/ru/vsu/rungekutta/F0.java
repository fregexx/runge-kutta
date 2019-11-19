package ru.vsu.rungekutta;

public class F0 implements Function {

    @Override
    public double solve(double t, double[] x) {
        return t + x[0];
    }

    public double getExactValue(double t){
        return 2 * Math.exp(t) - t - 1;
    }
}
