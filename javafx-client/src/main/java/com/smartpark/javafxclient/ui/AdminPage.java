package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AdminPage {

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
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));

        Label welcome = new Label("Welcome, Admin");
        welcome.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Button findParking = new Button("Find Parking");
        Button addLot = new Button("Add Lot");
        Button updateLot = new Button("Update Lot");
        Button deleteLot = new Button("Delete Lot");

        String buttonStyle =
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;";

        findParking.setStyle(buttonStyle);
        addLot.setStyle(buttonStyle);
        updateLot.setStyle(buttonStyle);
        deleteLot.setStyle(buttonStyle);

        findParking.setPrefWidth(220);
        addLot.setPrefWidth(220);
        updateLot.setPrefWidth(220);
        deleteLot.setPrefWidth(220);

        mainContent.getChildren().addAll(
                welcome,
                findParking,
                addLot,
                updateLot,
                deleteLot
        );

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}
