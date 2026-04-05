package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ParkingPage {

    public VBox getView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(35));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Parking Options");
        title.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label subtitle = new Label("Available lots based on your role");
        subtitle.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #555555;"
        );

        VBox recommendedCard = new VBox(10);
        recommendedCard.setPadding(new Insets(20));
        recommendedCard.setMaxWidth(800);
        recommendedCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 14;"
        );

        Label recTitle = new Label("Recommended Lot");
        recTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label recLot = new Label("Lot A");
        recLot.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        Label recDetails = new Label("24 spaces available • Closest option for your role");
        recDetails.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #444444;"
        );

        recommendedCard.getChildren().addAll(recTitle, recLot, recDetails);

        HBox lotRow = new HBox(20);
        lotRow.setAlignment(Pos.CENTER);

        VBox lot1 = createLotCard("Lot A", "24 spaces available", "Available");
        VBox lot2 = createLotCard("Lot B", "8 spaces available", "Limited");
        VBox lot3 = createLotCard("Lot C", "0 spaces available", "Full");

        lotRow.getChildren().addAll(lot1, lot2, lot3);

        root.getChildren().addAll(title, subtitle, recommendedCard, lotRow);

        return root;
    }

    private VBox createLotCard(String lotName, String spaces, String status) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(18));
        card.setPrefWidth(220);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 14;"
        );

        Label lotTitle = new Label(lotName);
        lotTitle.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label spacesLabel = new Label(spaces);
        spacesLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Label statusLabel = new Label(status);
        statusLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #555555;"
        );

        card.getChildren().addAll(lotTitle, spacesLabel, statusLabel);

        return card;
    }
}