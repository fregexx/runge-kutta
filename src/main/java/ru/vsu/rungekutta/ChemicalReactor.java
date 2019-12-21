package ru.vsu.rungekutta;

import ru.vsu.rungekutta.systems.DiffSystem3;

import java.util.function.Function;

public class ChemicalReactor {
    private RungeKutta rungeKutta = new RungeKutta();
    private static final double epsilon = 0.1;
    int N = 180000;
    double a = 0;
    double b = 180;

    private double grad(double h, double J0, double Jh) {
        return (Jh - J0) / h;
    }

    public Solution gradMethod() {
        double beta = 1;
        double alpha = 1;

        while (true) {
            System.out.println("alpha = " + alpha);
            System.out.println("beta = " + beta);
            DiffSystem diffSystemA = new DiffSystem3(getTFunction(alpha));
            Solution solution = rungeKutta.solveSystem(diffSystemA, a, b, N);
            double h = solution.getH();
            double[][] xA = solution.getX();
            double j0 = diffSystemA.getJ(xA[xA.length - 1]);    // J(alpha)
            System.out.println("jA = " + j0);

            DiffSystem diffSystemAH = new DiffSystem3(getTFunction(alpha + h));
            double[][] xAH = rungeKutta.solveSystem(diffSystemAH, a, b, N).getX();
            double jH = diffSystemA.getJ(xAH[xA.length - 1]);    // J(alpha+h)
            System.out.println("jH = " + jH);

            double grad = grad(h, j0, jH);
            System.out.println("grad = " + grad);

            if(Math.abs(grad) < epsilon){
                System.out.println("Found solution. J = " + j0);
                return solution;
            }

            double alpha1 = alpha + beta * grad;
            System.out.println("alpha1 = " + alpha1);
            DiffSystem diffSystemB = new DiffSystem3(getTFunction(alpha1));
            double[][] xB = rungeKutta.solveSystem(diffSystemB, a, b, N).getX();
            double jB = diffSystemA.getJ(xB[xA.length - 1]);    // J(alpha1)

            System.out.println("jB = " + jB);

            if (jB > j0) {
                alpha = alpha1;
            } else {
                beta = beta / 2;
            }
            System.out.println("----------------------------");
        }
    }

    public Solution optimize() {
        int k = 0;
        double l = 0;
        double r = 10;
        double alpha;
        do {
            k++;
            System.out.println();
            System.out.println("k: " + k);
            System.out.println("l: " + l);
            System.out.println("r: " + r);

            double delta = epsilon / 2;
            alpha = (l + r) / 2;
            System.out.println("alpha: " + alpha);
            double u1 = alpha - delta / 2;
            double u2 = alpha + delta / 2;

            Function<Double, Double> tFunctionA = getTFunction(u1);
            DiffSystem diffSystemA = new DiffSystem3(tFunctionA);
            double[][] xA = rungeKutta.solveSystem(diffSystemA, a, b, N).getX();

            Function<Double, Double> tFunctionB = getTFunction(u2);
            DiffSystem diffSystemB = new DiffSystem3(tFunctionB);
            double[][] xB = rungeKutta.solveSystem(diffSystemB, a, b, N).getX();

            double jA = diffSystemA.getJ(xA[xA.length - 1]);
            double jB = diffSystemA.getJ(xB[xB.length - 1]);

            if (jA < jB) {
                l = u1;
            } else {
                r = u2;
            }
            System.out.println("jA: " + jA);
            System.out.println("jB: " + jB);
        } while (Math.abs(r - l) > epsilon);

        alpha = (l + r) / 2;
        Function<Double, Double> tFunction = getTFunction(alpha);
        DiffSystem diffSystem = new DiffSystem3(tFunction);
        Solution solution = rungeKutta.solveSystem(diffSystem, a, b, N);
        double[][] x = solution.getX();
        System.out.println("Result J: " + diffSystem.getJ(x[x.length - 1]));
        return solution;
    }

    public Function<Double, Double> getTFunction(double alpha) {
//        return t -> 900.0;
//        return t -> 373 + Math.pow(t / 180, alpha) * (1500 - 373);
        return t -> 373 + Math.sin(Math.pow(t / 180, alpha) * Math.PI / 2) * (1500 - 373);
    }

    public Function<Double, Double> getTFunction2(double alpha1, double alpha2) {
        return t -> 373 + 2 * (alpha1 - 373) / Math.PI * Math.atan(alpha2 * t);
    }

    public Solution solve() {
        Function<Double, Double> tFunction = getTFunction(50);
        DiffSystem diffSystem = new DiffSystem3(tFunction);
        Solution solution = rungeKutta.solveSystem(diffSystem, a, b, N);
        double[][] x = solution.getX();
        double J = diffSystem.getJ(x[x.length - 1]);
        System.out.println(J);
        return solution;
    }


}
