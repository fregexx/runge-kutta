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
import java.util.function.Function;

public class Main extends Application {

    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    void onActionSolve(ActionEvent event) {
        ChemicalReactor chemicalReactor = new ChemicalReactor();
//        Solution solution = chemicalReactor.solve();
//        Solution solution = chemicalReactor.optimize();
//        Solution solution = chemicalReactor.gradMethod();
        Solution solution = chemicalReactor.gradMethod2();
       /* int N = 100;
        double a = 0;
        double b = 1;
//        double b = 180;

        DiffSystem diffSystem = DiffSystems.system0;

        RungeKutta rungeKutta = new RungeKutta();
        Solution solution = rungeKutta.solveSystem(diffSystem, a, b, N);*/

        double[] t = solution.getT();
        double[][] x = solution.getX();
        int N = solution.getN();
        DiffSystem diffSystem = solution.getDiffSystem();
        Function<Double, Double> tFunction = chemicalReactor.getTFunction(9.936132812499999);
//        Function<Double, Double> tFunction2 = chemicalReactor.getTFunction2(9.936132812499999);

        drawChart(N, t, x, diffSystem);
//        drawTChart(N, t, tFunction);
//        drawErrorChart(N, t, x, diffSystem);
    }

    public void drawTChart(int n, double[] t, Function<Double, Double> function) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Температура");
        int step = t.length / 1000;
        for (int i = 0; i < n + 1; i+=step) {
            series.getData().add(new XYChart.Data<>(t[i], function.apply(t[i])));
        }
        chart.getData().add(series);
    }

    public void drawChart(int n, double[] t, double[][] results, DiffSystem diffSystem) {
        for (int f = 0; f < diffSystem.size(); f++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("X" + f);
            int step = results.length / 1000;
            for (int i = 0; i < n + 1; i += step) {
                series.getData().add(new XYChart.Data<>(t[i], results[i][f]));
            }
            chart.getData().add(series);
        }
    }

    public void drawErrorChart(int n, double[] t, double[][] results, DiffSystem diffSystem) {
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
//        chart.setCreateSymbols(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
