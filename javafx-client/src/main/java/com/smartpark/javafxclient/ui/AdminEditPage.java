package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminEditPage {

    public BorderPane getView() {

        BorderPane root = new BorderPane();

        VBox sidebar = new VBox(25);
        sidebar.setPadding(new Insets(25));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #0B5E3C;");

        Label appTitle = new Label("SmartPark");
        appTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label dashboard = new Label("Dashboard");
        Label parking = new Label("Parking");
        Label about = new Label("About");
        Label help = new Label("Help");

        String sidebarStyle =
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';";

        dashboard.setStyle(sidebarStyle);
        parking.setStyle(sidebarStyle);
        about.setStyle(sidebarStyle);
        help.setStyle(sidebarStyle);

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help);

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(35));
        mainContent.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("Admin Panel");
        title.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        VBox formCard = new VBox(15);
        formCard.setAlignment(Pos.CENTER_LEFT);
        formCard.setPadding(new Insets(25));
        formCard.setMaxWidth(500);
        formCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 16;"
        );

        Label formTitle = new Label("Add New Lot");
        formTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");

        TextField spacesField = new TextField();
        spacesField.setPromptText("Spaces");

        TextField roleField = new TextField();
        roleField.setPromptText("Role");

        String fieldStyle =
                "-fx-background-radius: 10;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10;";

        nameField.setStyle(fieldStyle);
        capacityField.setStyle(fieldStyle);
        spacesField.setStyle(fieldStyle);
        roleField.setStyle(fieldStyle);

        nameField.setMaxWidth(350);
        capacityField.setMaxWidth(350);
        spacesField.setMaxWidth(350);
        roleField.setMaxWidth(350);

        HBox buttonRow = new HBox(15);
        buttonRow.setAlignment(Pos.CENTER_LEFT);

        Button addLotButton = new Button("Add Lot");
        Button backButton = new Button("Back to Dashboard");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;";

        addLotButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);

        addLotButton.setPrefWidth(140);
        backButton.setPrefWidth(180);

        buttonRow.getChildren().addAll(addLotButton, backButton);

        formCard.getChildren().addAll(
                formTitle,
                nameField,
                capacityField,
                spacesField,
                roleField,
                buttonRow
        );

        VBox existingLotsCard = new VBox(12);
        existingLotsCard.setAlignment(Pos.CENTER_LEFT);
        existingLotsCard.setPadding(new Insets(25));
        existingLotsCard.setMaxWidth(500);
        existingLotsCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 16;"
        );

        Label existingTitle = new Label("Existing Lots");
        existingTitle.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;"
        );

        Label lotA = new Label("• Lot A   [ Edit ]   [ Delete ]");
        Label lotB = new Label("• Lot B   [ Edit ]   [ Delete ]");

        lotA.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        lotB.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        existingLotsCard.getChildren().addAll(existingTitle, lotA, lotB);

        mainContent.getChildren().addAll(title, formCard, existingLotsCard);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}
