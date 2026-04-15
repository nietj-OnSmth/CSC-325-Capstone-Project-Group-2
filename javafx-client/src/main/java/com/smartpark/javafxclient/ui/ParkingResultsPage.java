package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ParkingResultsPage displays the recommended parking lot to the user.
 *
 * This page receives dynamic data from the backend including:
 * - Lot name
 * - Available spaces
 * - Distance from destination
 * - Current status (AVAILABLE, LIMITED, FULL)
 *
 * It allows the user to review the recommendation and return to the dashboard.
 */
public class ParkingResultsPage {

    /**
     * ParkingResultsPage displays the recommended parking lot to the user.
     *
     * This page receives dynamic data from the backend including:
     * - Lot name
     * - Available spaces
     * - Distance from destination
     * - Current status (AVAILABLE, LIMITED, FULL)
     *
     * It allows the user to review the recommendation and return to the dashboard.
     */
    public VBox getView(Stage stage, String lotName, String spaces, String distance, String status, String role) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Parking Results");
        title.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        VBox resultsCard = new VBox(15);
        resultsCard.setAlignment(Pos.CENTER);
        resultsCard.setPadding(new Insets(30));
        resultsCard.setMaxWidth(500);
        resultsCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 16;"
        );

        Label bestLot = new Label("Best Lot: " + lotName);
        bestLot.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        Label spacesLabel = new Label("Open Spaces: " + spaces);
        spacesLabel.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Label distanceLabel = new Label("Distance: " + distance);
        distanceLabel.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Label statusLabel = new Label("Status: " + status);
        statusLabel.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Button backButton = new Button("Back to Dashboard");
        backButton.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;"
        );

        backButton.setPrefWidth(220);

        backButton.setOnAction(e -> {
            Scene dashboardScene;

            if ("FACULTY".equalsIgnoreCase(role)) {
                dashboardScene = new Scene(new FacultyDashboardPage().getView(stage), 1100, 650);
            } else {
                dashboardScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
            }

            stage.setScene(dashboardScene);
        });

        resultsCard.getChildren().addAll(
                bestLot,
                spacesLabel,
                distanceLabel,
                statusLabel,
                backButton
        );

        root.getChildren().addAll(title, resultsCard);

        return root;
    }
}