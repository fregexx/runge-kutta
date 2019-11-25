package ru.vsu.rungekutta.systems;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem3 extends DiffSystem {

    private final double[] U = {15.19, 8.18, 13.198, 3.524, 4723.7, 423.7, 204.41, 1.466E-6, 0.013, 0.09, 5.428E-6, 0.024, 5.92E-6};
    private final double[] E = {25E3, 25E3, 25E3, 25E3, 40E3, 40E3, 40E3, 20E3, 20E3, 20E3, 20E3, 20E3, 20E3};
    private final int m0 = 18;
    private final int[] m = {84, 56, 42, 28, 92, 16};
    private final double T = 900;
    private final int G = 3500;
    private final int Gp = 1750;
    private final double[] R = initR();
    private final int[] q = {0, 78, 140, 140, 0, 0};

    private double getT(java.util.function.Function<Double, Double> T, double t){
        return T.apply(t);
    }

    private double[] initR() {
        double[] R = new double[13];
        for (int i = 0; i < 13; i++) {
            R[i] = U[i] * Math.exp(23 - E[i] / T);
        }
        return R;
    }

    private double p(double t) { //давление на расст t
        return 5 - t / 60;
    }

    private double v(double t, double[] x, java.util.function.Function<Double, Double> T) {
        double sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += m[i] * x[i];
        }
        double vSum = 0;
        for (int i = 0; i < 6; i++) {
            vSum += m[i] * x[i] / (T. * (G + Gp * (sum / m0)));
        }
        return 509.209 * p(t) * vSum;
    }

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(
                (t, x) -> -(R[0] + R[1] + R[2] + R[3]) * x[0] * v(t, x),
                (t, x) -> (R[2] * x[0] - (R[5] + R[6] + R[9] + R[12]) * x[1]) * v(t, x),
                (t, x) -> (R[1] * x[0] + R[5] * x[1] - (R[4] + R[8] + R[11]) * x[2]) * v(t, x),
                (t, x) -> (R[0] * x[0] + R[6] * x[1] + R[5] * x[2] - (R[7] + R[10]) * x[3]) * v(t, x),
                (t, x) -> (R[9] * x[1] + R[8] * x[2] + R[7] * x[3]) * v(t, x),
                (t, x) -> (R[3] * x[0] + R[12] * x[1] + R[11] * x[2] + R[10] * x[3]) * v(t, x));
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public double getExactValue(int functionIndex, double t) {
        return 0;
    }

    public double J(double t, double[]values) {
        double sumChisl = 0;
        double sumZnam = 0;
        for (int i = 1; i < 4; i++) {
            sumChisl += q[i] * m[i] * values[i];
        }
        for (int i = 0; i < 6; i++) {
            sumZnam += m[i] * values[i];
        }
        return sumChisl / sumZnam;
    }
}
