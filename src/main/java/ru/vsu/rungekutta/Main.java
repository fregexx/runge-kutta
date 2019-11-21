package ru.vsu.rungekutta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    void onActionSolve(ActionEvent event) {
        double a = 0;
        double b = 1;
        int n = 100;
        double[] t = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            t[i] = a + (b - a) * i / n;
        }
        double h = t[1] - t[0];

        solveSystem(n, h, t, DiffSystems.system1);
        System.out.println();
    }

    public void solveSystem(int n, double h, double[] t, DiffSystem system) {
        List<Function> functions = system.getFunctions();
        List<Double> initialConditions = system.getInitialConditions();
        RungeKutta rungeKutta = new RungeKutta();
        double[] x1 = new double[n + 1];
        double[] x2 = new double[n + 1];
        x1[0] = initialConditions.get(0);
        x2[0] = initialConditions.get(1);

        for (int i = 0; i < n; i++) {
            x1[i + 1] = rungeKutta.solve(functions.get(0), h, t[i], new double[]{x1[i], x2[i]}, x1[i]);
            x2[i + 1] = rungeKutta.solve(functions.get(1), h, t[i], new double[]{x1[i], x2[i]}, x2[i]);
        }

        printErrorValues(n, t, x1, functions.get(0));
        printErrorValues(n, t, x2, functions.get(1));

//        drawChart(n, t, x1, x2, system);
        drawErrorChart(n, t, x1, x2, system);
    }

    private void printErrorValues(int n, double[] t, double[] x, Function function) {
        for (int i = 0; i < n; i++) {
            double exactValue = function.exactValue(t[i]);
            System.out.println("Погрешность = " + Math.abs(x[i] - exactValue));
        }
    }

    private void drawChart(int n, double[] t, double[] x, double[] x2, DiffSystem diffSystem) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series1.setName("X1");
        series2.setName("X2");

        for (int i = 0; i < n + 1; i++) {
            series1.getData().add(new XYChart.Data<>(t[i], Math.abs(x[i])));
            series2.getData().add(new XYChart.Data<>(t[i], Math.abs(x2[i])));
        }
        chart.setCreateSymbols(false);
        chart.getData().add(series1);
        chart.getData().add(series2);
    }

    private void drawErrorChart(int n, double[] t, double[] x, double[] x2, DiffSystem diffSystem) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series1.setName("Погрешность X1");
        series2.setName("Погрешность X2");

        for (int i = 0; i < n + 1; i++) {
            series1.getData().add(new XYChart.Data<>(t[i], Math.abs(x[i] - diffSystem.getFunctions().get(0).exactValue(t[i]))));
            series2.getData().add(new XYChart.Data<>(t[i], Math.abs(x2[i] - diffSystem.getFunctions().get(1).exactValue(t[i]))));
        }
        chart.setCreateSymbols(false);
        chart.getData().add(series1);
        chart.getData().add(series2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
