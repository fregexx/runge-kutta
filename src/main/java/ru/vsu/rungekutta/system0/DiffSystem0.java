package ru.vsu.rungekutta.system0;

import ru.vsu.rungekutta.DiffSystem;
import ru.vsu.rungekutta.Function;

import java.util.Arrays;
import java.util.List;

public class DiffSystem0 extends DiffSystem {

    @Override
    public List<Function> getFunctions() {
        return Arrays.asList(new F0());
    }

    @Override
    public List<Double> getInitialConditions() {
        return Arrays.asList(1.0);
    }
}
