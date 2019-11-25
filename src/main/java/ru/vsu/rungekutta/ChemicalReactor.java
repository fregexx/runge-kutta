package ru.vsu.rungekutta;

public class ChemicalReactor {
    //    private static final int a = 0;
//    private static final int b = 50;
    private static final double epsilon = 0.01;
    private RungeKutta rungeKutta = new RungeKutta();

    public void optimize() {
        int a = 0;
        int b = 50;
        double alpha = (a + b) / 2;
        do {
            double T = T(t, alpha);
        } while (b - a < epsilon);
    }

    private double T(double t, double alpha) {
        return 373 + alpha * t;
    }
}
