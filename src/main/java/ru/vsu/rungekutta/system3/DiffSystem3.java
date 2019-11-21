package ru.vsu.rungekutta.system3;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;
import ru.vsu.rungekutta.system2.F1;
import ru.vsu.rungekutta.system2.F2;

import java.util.Arrays;
import java.util.List;

public class DiffSystem3 extends DiffSystem {

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F1(), new F2());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(3.0, 0.0);
    }
}
