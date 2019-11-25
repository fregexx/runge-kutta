package ru.vsu.rungekutta;

public class RungeKutta {

//    public DiffSystem solveSystem(DiffSystem diffSystem){
//        double[][] values = diffSystem.getValues();
//        for (int n = 0; n < N; n++) {
//            for (int f = 0; f < diffSystem.getFunctions().size(); f++) {
//                results[i+1][f] = solve(diffSystem.getFunction(f), h, t[i], diffSystem.getValues(n), diffSystem.getValue(n,f));
//            }
//        }
//    }

    public double solve(Function f, double h, double t, double[] x, double xPrev) {
        double[] k = getK(h, t, x, f);
        return approxY(h, xPrev, k);
    }

    double approxY(double h, double xPrev, double[] k) {
        return xPrev + h * (k[0] + 2 * k[1] + 2 * k[2] + k[3]) / 6;
    }

    double[] getK(double h, double t, double[] x, Function f) {
        double[] k = new double[4];
        k[0] = f.solve(t, x);
        k[1] = f.solve(t + h / 2, X(x, h / 2, k[0]));
        k[2] = f.solve(t + h / 2, X(x, h / 2, k[1]));
        k[3] = f.solve(t + h, X(x, h, k[2]));
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
