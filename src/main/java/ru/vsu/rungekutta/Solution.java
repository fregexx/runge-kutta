package ru.vsu.rungekutta;

import lombok.Getter;

@Getter
public class Solution {
    private int N;
    private double a;
    private double b;
    private double h;
    private double[] t;
    private double[][] x;
    private DiffSystem diffSystem;

    public Solution(double a, double b, int N, DiffSystem diffSystem) {
        this.N = N;
        this.a = a;
        this.b = b;
        this.diffSystem = diffSystem;
        initT();
        initX();
        initH();
    }

    private void initT() {
        this.t = new double[N + 1];
        for (int i = 0; i < N + 1; i++) {
            t[i] = a + (b - a) * i / N;
        }
    }

    private void initX() {
        this.x = new double[N + 1][diffSystem.size()];
        for (int f = 0; f < diffSystem.getFunctions().size(); f++) {
            x[0][f] = diffSystem.getInitialConditions().get(f);
        }
    }

    private void initH() {
        this.h = t[1] - t[0];
    }
}
