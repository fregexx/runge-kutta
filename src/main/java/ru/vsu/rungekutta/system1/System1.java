//package ru.vsu.rungekutta.system1;
//
//import ru.vsu.rungekutta.DiffSystem;
//import ru.vsu.rungekutta.Function;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class System1 extends DiffSystem {
//
//    public System1(List<Function> functions, List<Double> initialConditions) {
//        this.functions = Arrays.asList(
//                (t, x) -> 2*x[0] + Math.exp(t),
//                (t, x) -> x[0] + x[1] - Math.exp(t));
//        this.initialConditions = Arrays.asList(1.0, 0.0);
//        this.values = new double[N + 1][functions.size()];
//    }
//
//    @Override
//    public List<Function> getFunctions() {
//        return Arrays.asList(
//                (t, x) -> 2*x[0] + Math.exp(t),
//                (t, x) -> x[0] + x[1] - Math.exp(t));
//    }
//
//    @Override
//    public List<Double> getInitialConditions() {
//        return Arrays.asList(1.0, 0.0);
//    }
//
//    @Override
//    public Function getFunction(int functionIndex) {
//        return null;
//    }
//
//    @Override
//    public double[] getValues(int step) {
//        return new double[0];
//    }
//}
