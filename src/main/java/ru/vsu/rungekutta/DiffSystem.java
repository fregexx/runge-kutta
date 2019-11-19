package ru.vsu.rungekutta;

import java.util.List;

public abstract class DiffSystem {
    public abstract List<Function> getFunctions();
    public abstract List<Double> getInitialConditions();
}
