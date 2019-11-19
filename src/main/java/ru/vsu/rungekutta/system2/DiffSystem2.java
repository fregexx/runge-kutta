package ru.vsu.rungekutta.system2;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem2 extends DiffSystem {

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F1(), new F2());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(3.0, 0.0);
    }
}
