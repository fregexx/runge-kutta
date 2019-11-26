package ru.vsu.rungekutta;

import ru.vsu.rungekutta.systems.DiffSystem3;

import java.util.function.Function;

public class ChemicalReactor {
    private RungeKutta rungeKutta = new RungeKutta();
    private static final double epsilon = 0.1;
    int N = 180000;
    double a = 0;
    double b = 180;

    public Solution optimize() {
        int k = 0;
        double l = 0;
        double r = 10000;
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

    private Function<Double, Double> getTFunction(double alpha) {
//        return t -> 900.0;
//        return t -> 373 + Math.pow(t / 180, alpha) * (1500 - 373);
        return t -> 373 + Math.sin(Math.pow(t / 180, alpha) * Math.PI / 2) * (1500 - 373);
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
