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
 * HelpPage provides instructions for using the SmartPark application.
 *
 * This page explains how users can choose a role, view parking options,
 * and select the best lot. It also allows the user to return to the
 * Student Dashboard.
 */
public class HelpPage {

    /**
     * Builds and returns the Help page UI.
     *
     * @param stage the primary JavaFX stage used for navigation
     * @return VBox layout containing the Help page content
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

        Label title = new Label("Help & Support");
        title.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label subtitle = new Label("How to use SmartPark");
        subtitle.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #555555;"
        );

        VBox section1 = createSection(
                "Choose Your Role",
                "Start by selecting whether you are a student, faculty member, or visitor on the home screen. This helps the system show you the correct parking options."
        );

        VBox section2 = createSection(
                "View Parking Options",
                "After selecting your role, you will be able to view available parking lots along with their current availability and location information."
        );

        VBox section3 = createSection(
                "Select the Best Lot",
                "Choose the parking lot that best fits your needs based on availability and convenience. Recommended options will be highlighted for you."
        );

        Label footer = new Label(
                "If you need additional assistance, please contact campus parking services."
        );
        footer.setWrapText(true);
        footer.setMaxWidth(700);
        footer.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #444444;"
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

        // Returns the user to the Student/Faculty Dashboard.
        backButton.setOnAction(e -> {
            String role = UserSession.getRole();

            if ("FACULTY".equalsIgnoreCase(role)) {
                Scene facultyScene = new Scene(new FacultyDashboardPage().getView(stage), 1100, 650);
                stage.setScene(facultyScene);
            } else {
                Scene studentScene = new Scene(new StudentDashboardPage().getView(stage), 1100, 650);
                stage.setScene(studentScene);
            }
        });

        content.getChildren().addAll(
                title,
                subtitle,
                section1,
                section2,
                section3,
                footer,
                backButton
        );

        root.setLeft(sidebar);
        root.setCenter(content);

        return root;
    }

    /**
     * Creates a styled content section for the Help page.
     *
     * @param heading the title of the help section
     * @param text the instructional text for that section
     * @return a VBox containing the styled section
     */
    private VBox createSection(String heading, String text) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(20));
        box.setMaxWidth(750);
        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #D9E2DD;" +
                        "-fx-border-radius: 14;"
        );

        Label headingLabel = new Label(heading);
        headingLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-family: 'Helvetica';" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #0B5E3C;"
        );

        Label textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-text-fill: #333333;"
        );

        box.getChildren().addAll(headingLabel, textLabel);

        return box;
    }
}