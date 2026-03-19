package com.smartpark.javafxclient;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmartParkApp extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #1e293b;");

        Label appTitle = new Label("SmartPark");
        appTitle.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");

        Label dashboard = new Label("Dashboard");
        dashboard.setStyle("-fx-text-fill: white;");

        Label parking = new Label("Parking");
        parking.setStyle("-fx-text-fill: white;");

        Label about = new Label("About");
        about.setStyle("-fx-text-fill: white;");

        Label help = new Label("Help");
        help.setStyle("-fx-text-fill: white;");

        sidebar.getChildren().addAll(appTitle, dashboard, parking, about, help);

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));

        Label welcomeTitle = new Label("Welcome to SmartPark");
        welcomeTitle.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        Label subtitle = new Label("Find the best parking available");
        subtitle.setStyle("-fx-text-fill: #555555;");

        Button studentButton = new Button("Student");
        Button facultyButton = new Button("Faculty");
        Button visitorButton = new Button("Visitor");

        studentButton.setPrefWidth(220);
        studentButton.setPrefHeight(50);

        facultyButton.setPrefWidth(220);
        facultyButton.setPrefHeight(50);

        visitorButton.setPrefWidth(220);
        visitorButton.setPrefHeight(50);

        mainContent.getChildren().addAll(
                welcomeTitle,
                subtitle,
                studentButton,
                facultyButton,
                visitorButton
        );

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("SmartPark Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}