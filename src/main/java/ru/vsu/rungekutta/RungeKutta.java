package ru.vsu.rungekutta;

import java.util.List;

public class RungeKutta {

    public Solution solveSystem(DiffSystem diffSystem, double a, double b, int N) {
        Solution solution = new Solution(a, b, N, diffSystem);
        double[] t = solution.getT();
        double[][] x = solution.getX();
        double h = solution.getH();
        List<Function> functions = diffSystem.getFunctions();
        for (int i = 0; i < N; i++) {
            for (int f = 0; f < functions.size(); f++) {
                x[i + 1][f] = solve(functions.get(f), h, t[i], x[i], x[i][f]);
//                System.out.println();
            }
        }
        return solution;
    }

    public double solve(Function f, double h, double t, double[] x, double xPrev) {
        double[] k = getK(h, t, x, f);
        return approxY(h, xPrev, k);
    }

    double approxY(double h, double xPrev, double[] k) {
        return xPrev + h * (k[0] + 2 * k[1] + 2 * k[2] + k[3]) / 6;
    }

    double[] getK(double h, double t, double[] x, Function f) {
        double[] k = new double[4];
        k[0] = f.apply(t, x);
        k[1] = f.apply(t + h / 2, X(x, h / 2, k[0]));
        k[2] = f.apply(t + h / 2, X(x, h / 2, k[1]));
        k[3] = f.apply(t + h, X(x, h, k[2]));
        return k;
    }

    private double[] X(double[] x, double h, double k) {
        double[] newX = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            newX[i] = x[i] + h * k;
        }
        return newX;
    }
}
