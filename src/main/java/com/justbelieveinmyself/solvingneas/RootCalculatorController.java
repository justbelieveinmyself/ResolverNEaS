package com.justbelieveinmyself.solvingneas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class RootCalculatorController {

    @FXML
    private TextField guessATextField;

    @FXML
    private TextField guessBTextField;

    @FXML
    private TextField toleranceTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private ListView<String> iterationListView;

    private ObservableList<String> iterations = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        iterationListView.setItems(iterations);
    }

    @FXML
    protected void calculateBisectionRoot() {
        iterations.clear();

        double a = Double.parseDouble(guessATextField.getText());
        double b = Double.parseDouble(guessBTextField.getText());
        double tolerance = Double.parseDouble(toleranceTextField.getText());

        double c;
        int iterationCount = 0;
        do {
            c = (a + b) / 2;
            double fA = f(a);
            double fC = f(c);
            if (fA * fC < 0) {
                b = c;
            } else {
                a = c;
            }
            iterationCount++;
            iterations.add("Bisection Iteration " + iterationCount + ": Root = " + c);
        } while (Math.abs(b - a) > tolerance);

        resultLabel.setText("Bisection Root: " + c + "\nIterations: " + iterationCount);
    }

    @FXML
    protected void calculateNewtonRaphsonRoot() {
        iterations.clear();

        double x0 = Double.parseDouble(guessATextField.getText()); // Initial guess
        double tolerance = Double.parseDouble(toleranceTextField.getText());

        double x = x0;
        int iterationCount = 0;
        double fx;
        do {
            double f1x = f1(x); // Derivative
            fx = f(x);
            x = x - fx / f1x;
            iterationCount++;
            iterations.add("Newton-Raphson Iteration " + iterationCount + ": Root = " + x);
        } while (Math.abs(fx) > tolerance);

        resultLabel.setText("Newton-Raphson Root: " + x + "\nIterations: " + iterationCount);
    }

    private double f(double x) {
        return Math.pow(x, 3) - 2 * x - 5; // x^3 - 2x - 5
    }

    private double f1(double x) {
        return 3 * Math.pow(x, 2) - 2; // 3x^2 - 2
    }
}
