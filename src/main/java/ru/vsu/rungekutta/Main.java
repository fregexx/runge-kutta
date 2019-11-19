package ru.vsu.rungekutta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    @FXML
    private Canvas canvas;

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

        double[] y = new double[n + 1];
        double[] x1 = new double[n + 1];
        double[] x2 = new double[n + 1];
        y[0] = 1;
        x1[0] = 1;
        x2[0] = 0;

        double h = x[1] - x[0];
        RungeKutta rungeKutta = new RungeKutta();

        for (int i = 0; i < n; i++) {
//            y[i + 1] = rungeKutta.test(h, x[i], y[i]);
//            x1[i + 1] = rungeKutta.solve(new F0(), h, t[i], new double[]{x1[i]}, x1[i]);
            x1[i + 1] = rungeKutta.solve(new F1(), h, t[i], new double[]{x1[i]}, x1[i]);
            x2[i + 1] = rungeKutta.solve(new F2(), h, t[i], new double[]{x1[i], x2[i]}, x2[i]);

            double exactValueF1 = new F1().exactValue(x[i + 1]);
            double exactValueF2 = new F1().exactValue(x[i + 1]);
//            System.out.println("Погрешность1 = " + Math.abs(y[i + 1] - exactValue));
            System.out.println("ПогрешностьF1 = " + Math.abs(x1[i + 1] - exactValueF1));
            System.out.println("ПогрешностьF2 = " + Math.abs(x2[i + 1] - exactValueF1));
        }


        System.out.println();
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
