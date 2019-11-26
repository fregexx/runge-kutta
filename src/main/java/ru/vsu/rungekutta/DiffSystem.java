package ru.vsu.rungekutta;

import lombok.Getter;

import java.util.List;

public abstract class DiffSystem {
    private List<Function> functions;
    private List<Double> initialConditions;

//    public DiffSystem(List<Function> functions, List<Double> initialConditions) {
//        this.functions = functions;
//        this.initialConditions = initialConditions;
//    }

    public int size(){
        return getFunctions().size();
    }

    public abstract List<Function> getFunctions();
    public abstract List<Double> getInitialConditions();
    public abstract double getExactValue(int functionIndex, double t);

    public double J(double[] values){
        return 0;
    }
//
//    public void init(int a, int b, int N) {
//        initGrid(a, b, N);
//        initInitialConditions(N);
//    }

//    private void initGrid(int a, int b, int N) {
//        this.t = new double[N + 1];
//        for (int i = 0; i < N + 1; i++) {
//            t[i] = a + (b - a) * i / N;
//        }
//    }

//    private void initInitialConditions(int N) {
//        this.values = new double[N + 1][functions.size()];
//        for (int f = 0; f < functions.size(); f++) {
//            this.values[0][f] = initialConditions.get(f);
//        }
//    }
//
//    public Function getFunction(int functionIndex) {
//        return this.functions.get(functionIndex);
//    }

//    public double[] getValues(int n) {
//        return this.values[n];
//    }
//
//    public double getValue(int n, int functionIndex) {
//        return this.values[n][functionIndex];
//    }

}
