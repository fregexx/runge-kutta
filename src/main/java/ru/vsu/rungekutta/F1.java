package ru.vsu.rungekutta;

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
