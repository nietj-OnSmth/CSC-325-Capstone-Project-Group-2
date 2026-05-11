package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

/**
 * AboutPage displays background information about the SmartPark system.
 *
 * This page is available to Student and Faculty users and uses
 * UserSession to return each user to the correct role-based dashboard.
 */
public class AboutPage {

    /**
     * Builds and returns the About page UI.
     *
     * @param stage the primary JavaFX stage used for navigation
     * @return VBox layout containing the About page content
     */
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
                        "-fx-padding: 8 12;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: transparent;";

        Button dashboardButton = new Button("Dashboard");
        Button parkingButton = new Button("Parking");
        Button aboutButton = new Button("About");
        Button helpButton = new Button("Help");
        Button logoutButton = new Button("Logout");

        dashboardButton.setStyle(sidebarButtonStyle);
        parkingButton.setStyle(sidebarButtonStyle);
        aboutButton.setStyle(sidebarButtonStyle);
        helpButton.setStyle(sidebarButtonStyle);
        logoutButton.setStyle(sidebarButtonStyle);

        Button[] sidebarButtons = {
                dashboardButton,
                parkingButton,
                aboutButton,
                helpButton,
                logoutButton
        };

        for (Button button : sidebarButtons) {
            button.setOnMouseEntered(e -> button.setStyle(
                    sidebarButtonStyle +
                            "-fx-background-color: rgba(255,255,255,0.15);" +
                            "-fx-background-radius: 8;" +
                            "-fx-padding: 8 12;"
            ));

            button.setOnMouseExited(e -> button.setStyle(sidebarButtonStyle));
        }

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        parkingButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                appTitle,
                dashboardButton,
                parkingButton,
                aboutButton,
                helpButton,
                logoutButton
        );

        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #F5F7F6;");

        Label title = new Label("About SmartPark");
        title.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label subtitle = new Label("A smarter way to find campus parking");
        subtitle.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #555555;"
        );

        VBox contentBox = new VBox(15);
        contentBox.setMaxWidth(750);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 14;"
        );

        Label paragraph1 = new Label(
                "SmartPark is a full-stack campus parking management system designed to help students and faculty quickly locate and reserve parking based on real-time availability and proximity. The system provides role-based access, parking recommendations, reservation management, and administrative parking lot controls through an integrated JavaFX frontend and Spring Boot backend."
        );
        paragraph1.setWrapText(true);
        paragraph1.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        Button backButton = new Button("Back to Dashboard");
        backButton.setStyle(
                "-fx-background-color: #0B5E3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;"
        );

        // Sends the user back to the correct role-based dashboard.
        dashboardButton.setOnAction(e -> navigateToDashboard(stage));

        /**
         * For student/faculty users, Parking returns them to their dashboard.
         * From there, they can request a recommendation using the dashboard button.
         * */
        parkingButton.setOnAction(e -> navigateToDashboard(stage));

        // Reloads the About page.
        aboutButton.setOnAction(e -> {
            Scene aboutScene = new Scene(new AboutPage().getView(stage), 1100, 650);
            stage.setScene(aboutScene);
        });

        // Opens the Help page.
        helpButton.setOnAction(e -> {
            Scene helpScene = new Scene(new HelpPage().getView(stage), 1100, 650);
            stage.setScene(helpScene);
        });

        // Logs the user out and clears the frontend session.
        logoutButton.setOnAction(e -> {
            UserSession.clear();

            Scene loginScene = new Scene(new LoginPage().getView(stage), 1100, 650);
            stage.setScene(loginScene);
        });

        // Returns the user to the correct Student/Faculty dashboard.
        backButton.setOnAction(e -> navigateToDashboard(stage));

        contentBox.getChildren().addAll(paragraph1);

        content.getChildren().addAll(title, subtitle, contentBox, backButton);

        root.setLeft(sidebar);
        root.setCenter(content);

        return root;
    }
    /**
    * Navigates the user back to the correct dashboard based on the role
    * stored in UserSession.
    */
    void navigateToDashboard(Stage stage) {
        String role = UserSession.getRole();

        if ("FACULTY".equalsIgnoreCase(role)) {
            Scene facultyScene = new Scene(new FacultyDashboardPage().getView(stage), 1100, 650);
            stage.setScene(facultyScene);
        } else {
            Scene studentScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
            stage.setScene(studentScene);
        }
    }
}