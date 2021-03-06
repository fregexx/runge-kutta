package ru.vsu.rungekutta.systems;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem0 extends DiffSystem {

    private List<java.util.function.Function<Double, Double>> exactFunctions = Arrays.asList(
            t -> 2 * Math.exp(t) - t - 1);

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList((t, x) -> t + x[0]);
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(1.0);
    }

    public double getExactValue(int functionIndex, double t) {
        return exactFunctions.get(functionIndex).apply(t);
    }
}
