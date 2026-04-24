package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;


/**
 * AdminPage represents the main dashboard for administrator users.
 *
 * This page allows admins to:
 * - Navigate through admin-related pages
 * - Access parking management tools
 * - Open the admin editing interface for lot management
 * - View help/about information
 * - Log out of the system
 */
public class AdminPage {

    public BorderPane getView(Stage stage) {

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

        String sidebarButtonStyle =
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-alignment: CENTER-LEFT;" +
                        "-fx-padding: 0;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;";


        Button dashboardButton = new Button("Dashboard");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Button logoutButton = new Button("Logout");

        dashboardButton.setStyle(sidebarButtonStyle);
        aboutButton.setStyle(sidebarButtonStyle);
        helpButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                appTitle,
                dashboardButton,
                aboutButton,
                helpButton,
                logoutButton
        );

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: #F5F7F6;");

        Label welcome = new Label("Welcome, Admin");
        welcome.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 30px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;"
        );

        Label role = new Label("Logged in as: Admin");
        role.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                        "-fx-font-family: 'Helvetica';"
        );

        Button viewAllLots = new Button("View All Lots");
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

        viewAllLots.setStyle(buttonStyle);
        addLot.setStyle(buttonStyle);
        updateLot.setStyle(buttonStyle);
        deleteLot.setStyle(buttonStyle);

        viewAllLots.setPrefWidth(220);
        addLot.setPrefWidth(220);
        updateLot.setPrefWidth(220);
        deleteLot.setPrefWidth(220);

        dashboardButton.setOnAction(e -> {
            Scene dashboardScene = new Scene(new AdminPage().getView(stage), 1100, 650);
            stage.setScene(dashboardScene);
        });

        aboutButton.setOnAction(e -> {
            Scene aboutScene = new Scene(new AboutPage().getView(stage), 1100, 650);
            stage.setScene(aboutScene);
        });

        helpButton.setOnAction(e -> {
            Scene helpScene = new Scene(new HelpPage().getView(stage), 1100, 650);
            stage.setScene(helpScene);
        });

        logoutButton.setOnAction(e -> {
            UserSession.clear(); // clears session

            Scene loginScene = new Scene(new LoginPage().getView(stage), 1100, 650);
            stage.setScene(loginScene);
        });

        /**
         * Opens the admin edit page where all lots can be viewed and managed.
         */
        viewAllLots.setOnAction(e -> {
            Scene adminEditScene = new Scene(new AdminEditPage().getView(stage), 1100, 650);
            stage.setScene(adminEditScene);
        });

        addLot.setOnAction(e -> {
            Scene adminEditScene = new Scene(new AdminEditPage().getView(stage), 1100, 650);
            stage.setScene(adminEditScene);
        });

        updateLot.setOnAction(e -> {
            Scene adminEditScene = new Scene(new AdminEditPage().getView(stage), 1100, 650);
            stage.setScene(adminEditScene);
        });

        deleteLot.setOnAction(e -> {
            Scene adminEditScene = new Scene(new AdminEditPage().getView(stage), 1100, 650);
            stage.setScene(adminEditScene);
        });

        mainContent.getChildren().addAll(
                welcome,
                role,
                viewAllLots,
                addLot,
                updateLot,
                deleteLot
        );

        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return root;
    }
}