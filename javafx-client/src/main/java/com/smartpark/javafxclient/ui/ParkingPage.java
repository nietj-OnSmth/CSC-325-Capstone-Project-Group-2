package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ParkingPage {

    public VBox getView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Parking Results");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label bestLot = new Label("Best Lot: A");
        Label spaces = new Label("Open Spaces: 25");
        Label distance = new Label("Distance: 0.3 miles");

        bestLot.setStyle("-fx-font-size: 16px;");
        spaces.setStyle("-fx-font-size: 16px;");
        distance.setStyle("-fx-font-size: 16px;");

        Button nextButton = new Button("Next Best Lot");
        Button backButton = new Button("Back to Dashboard");

        nextButton.setPrefWidth(200);
        backButton.setPrefWidth(200);

        root.getChildren().addAll(
                title,
                bestLot,
                spaces,
                distance,
                nextButton,
                backButton
        );

        return root;
    }
}