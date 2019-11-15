package ru.vsu.rungekutta;

public class RungeKutta {
    double approxY(double y, double h, double[] k) {
        return y + h * (k[0] + 2 * k[1] + 2 * k[2] + k[3]) / 6;
    }

    double[] getK(double h, ){

    }
}
