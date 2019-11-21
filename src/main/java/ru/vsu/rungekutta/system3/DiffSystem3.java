package ru.vsu.rungekutta.system3;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;
import ru.vsu.rungekutta.system2.F1;
import ru.vsu.rungekutta.system2.F2;

import java.util.Arrays;
import java.util.List;

public class DiffSystem3 extends DiffSystem {

    private double[] U = {15.19, 8.18, 13.198, 3.524, 4723.7, 423.7, 204.41, 1.466E-6, 0.013, 0.09, 5.428E-6, 0.024, 5.92E-6};
    private double[] E = {25E3, 25E3, 25E3, 25E3, 40E3, 40E3, 40E3, 20E3, 20E3, 20E3, 20E3, 20E3, 20E3};
    private int m0 = 18;
    private int[] m = {84, 56, 42, 28, 92, 16};
    private double T = 900;
    private int G = 3500;
    private int Gp = 1750;

    public double R(int i) {
        return U[i] * Math.exp(23 - E[i] / T);
    }

    private double p(double t) { //давление на расст t
        return 5 - t / 60;
    }

    public double v(double t, double[] x) {
        double sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += m[i] * x[i];
        }
        double vSum = 0;
        for (int i = 0; i < 6; i++) {
            vSum += m[i] * x[i] / (T * (G + Gp * (sum / m0)));
        }
        return 509.209 * p(t) * vSum;
    }

    public double f1(double t, double[] x) {
        return
    }

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F1(), new F2());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
}
