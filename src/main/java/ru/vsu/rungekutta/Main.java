package ru.vsu.rungekutta;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    @FXML
    private HBox hbox;

    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    void onActionDrawCircle(ActionEvent event) {
        double a = 0;
        double b = 1;
        int n = 10;
        double[] x = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            x[i] = a + (b - a) * i / n;
        }
        double[] t = Arrays.copyOf(x, x.length);

        double[] x1 = new double[n + 1];
        double[] x2 = new double[n + 1];
        x1[0] = 1;
        x2[0] = 0;

        double h = x[1] - x[0];
        RungeKutta rungeKutta = new RungeKutta();

        for (int i = 0; i < n; i++) {
            x1[i + 1] = rungeKutta.solve(new F0(), h, t[i], new double[]{x1[i]}, x1[i]);
//            x1[i + 1] = rungeKutta.solve(new F1(), h, t[i], new double[]{x1[i]}, x1[i]);
//            x2[i + 1] = rungeKutta.solve(new F2(), h, t[i], new double[]{x1[i], x2[i]}, x2[i]);

            double exactValue = new F0().exactValue(x[i + 1]);
//            double exactValueF1 = new F1().exactValue(x[i + 1]);
//            double exactValueF2 = new F2().exactValue(x[i + 1]);
            System.out.println("Погрешность1 = " + Math.abs(x1[i + 1] - exactValue));
//            System.out.println("ПогрешностьF1 = " + Math.abs(x1[i + 1] - exactValueF1));
//            System.out.println("ПогрешностьF2 = " + Math.abs(x2[i + 1] - exactValueF2));
        }

        drawErrorChart(n, t, x1, new F0());

        System.out.println();
    }

    private void drawErrorChart(int n, double[] t, double[] x, Function function) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Погрешность");

        for (int i = 0; i < n; i++) {
            double exactValue = function.exactValue(t[i]);
            series1.getData().add(new XYChart.Data<>(t[i], Math.abs(x[i] - exactValue)));
        }
        chart.getData().add(series1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
