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
        int N = 50;
        double a = 0;
//        double b = 1;
        double b = 180;

        double[] t = new double[N + 1];
        for (int i = 0; i < N + 1; i++) {
            t[i] = a + (b - a) * i / N;
        }

        double h = t[1] - t[0];

        DiffSystem diffSystem = DiffSystems.system3;

        RungeKutta rungeKutta = new RungeKutta();

        List<Function> functions = diffSystem.getFunctions();
        double[][] results = new double[N + 1][diffSystem.size()];
        for (int f = 0; f < functions.size(); f++) {
            results[0][f] = diffSystem.getInitialConditions().get(f);
        }

        for (int i = 0; i < N; i++) {
            for (int f = 0; f < functions.size(); f++) {
                results[i + 1][f] = rungeKutta.solve(functions.get(f), h, t[i], results[i], results[i][f]);
            }
        }
//        drawChart(N, t, results, diffSystem);
        drawErrorChart(N, t, results, diffSystem);
    }

    private void drawChart(int n, double[] t, double[][] results, DiffSystem diffSystem) {
        for (int f = 0; f < diffSystem.size(); f++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("X" + f);
            for (int i = 0; i < n + 1; i++) {
                series.getData().add(new XYChart.Data<>(t[i], results[i][f]));
            }
            chart.getData().add(series);
        }
    }

    private void drawErrorChart(int n, double[] t, double[][] results, DiffSystem diffSystem) {
        for (int f = 0; f < diffSystem.size(); f++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Погрешность X" + f);
            for (int i = 0; i < n + 1; i++) {
                series.getData().add(new XYChart.Data<>(t[i], Math.abs(results[i][f] - diffSystem.getExactValue(f, t[i]))));
            }
            chart.getData().add(series);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        chart.setCreateSymbols(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
