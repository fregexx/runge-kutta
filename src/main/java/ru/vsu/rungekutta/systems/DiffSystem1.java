package ru.vsu.rungekutta.systems;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem1 extends DiffSystem {

    private List<java.util.function.Function<Double, Double>> exactFunctions = Arrays.asList(
            t -> Math.exp(t) * t,
            t -> Math.exp(t) * (0.5 * t * t - t));

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(
                (t, x) -> 2 * x[0] + Math.exp(t),
                (t, x) -> x[0] + x[1] - Math.exp(t));
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(1.0, 0.0);
    }

    public double getExactValue(int functionIndex, double t) {
        return exactFunctions.get(functionIndex).apply(t);
    }

}
