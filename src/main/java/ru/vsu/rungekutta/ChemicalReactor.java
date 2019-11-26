package ru.vsu.rungekutta;

import ru.vsu.rungekutta.systems.DiffSystem3;

import java.util.function.Function;

public class ChemicalReactor {
    private RungeKutta rungeKutta = new RungeKutta();
    private static final double epsilon = 0.01;
    int N = 1800;
    double a = 0;
    double b = 180;

    public void optimize() {
        double alpha = (a + b) / 2;
        do {
            Function<Double, Double> tFunction = getTFunction(alpha);
            DiffSystem diffSystem = new DiffSystem3(tFunction);
            Solution solution = rungeKutta.solveSystem(diffSystem, a, b, N);
        } while (b - a < epsilon);
    }

    private Function<Double, Double> getTFunction(double alpha) {
        return t -> 1200.0;
//        return t -> 373 + Math.pow(t / 180, alpha) * (1500 - 373);
        /*return t -> {
            System.out.println(373 + Math.sin(Math.pow(t / 180, alpha) * Math.PI / 2) * (1500 - 373));
            return 373 + Math.sin(Math.pow(t / 180, alpha) * Math.PI / 2) * (1500 - 373);
        };*/
    }

    public Solution solve() {
//        Function<Double, Double> tFunction = t -> (double) 900; // J = 34.03911000970822
        Function<Double, Double> tFunction = getTFunction(50);
        DiffSystem diffSystem = new DiffSystem3(tFunction);
        Solution solution = rungeKutta.solveSystem(diffSystem, a, b, N);
        double[][] x = solution.getX();
        double J = diffSystem.J(x[x.length - 1]);
        System.out.println(J);
        return solution;
    }


}
