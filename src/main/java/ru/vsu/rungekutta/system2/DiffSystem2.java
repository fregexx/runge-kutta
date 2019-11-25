package ru.vsu.rungekutta.system2;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem2 extends DiffSystem {

    private List<java.util.function.Function<Double, Double>> exactFunctions = Arrays.asList(
            t -> 4 * Math.exp(-t) - Math.exp(2 * t),
            t -> Math.exp(-t) - Math.exp(2 * t));

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F1(), new F2());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(3.0, 0.0);
    }

    @Override
    public double getExactValue(int functionIndex, double t) {
        return exactFunctions.get(functionIndex).apply(t);
    }
}
