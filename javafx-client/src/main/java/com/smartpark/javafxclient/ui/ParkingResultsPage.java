package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ParkingResultsPage {

    public VBox getView() {
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

        Label bestLot = new Label("Best Lot: A");
        bestLot.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        Label spaces = new Label("Open Spaces: 25");
        spaces.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Label distance = new Label("Distance: 0.3 miles");
        distance.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Button nextBestButton = new Button("Next Best Lot");
        Button backButton = new Button("Back to Dashboard");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;";

        nextBestButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);

        nextBestButton.setPrefWidth(220);
        backButton.setPrefWidth(220);

        resultsCard.getChildren().addAll(
                bestLot,
                spaces,
                distance,
                nextBestButton,
                backButton
        );

        root.getChildren().addAll(title, resultsCard);

        return root;
    }
}