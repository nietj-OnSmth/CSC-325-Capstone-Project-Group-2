package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FacultyDashboardPage {

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
        Label logout = new Label("Logout");

        String sidebarStyle =
                "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';";

        dashboard.setStyle(sidebarStyle);
        parking.setStyle(sidebarStyle);
        about.setStyle(sidebarStyle);
        help.setStyle(sidebarStyle);
        logout.setStyle(sidebarStyle);

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help, logout);

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: #F5F7F6;");

        Label welcome = new Label("Welcome, Faculty");
        welcome.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label role = new Label("Logged in as: Faculty");
        role.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';"
        );

        Button findParking = new Button("Find Parking");
        findParking.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 12 24;" +
                        "-fx-cursor: hand;"
        );

        mainContent.getChildren().addAll(welcome, role, findParking);

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}