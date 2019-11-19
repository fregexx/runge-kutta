package ru.vsu.rungekutta.system1;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem1 extends DiffSystem {

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F1(), new F2());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(0.0, 0.0);
    }
}
