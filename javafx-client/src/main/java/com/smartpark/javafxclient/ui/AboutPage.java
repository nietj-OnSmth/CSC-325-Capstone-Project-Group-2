package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * AboutPage displays background information about the SmartPark system.
 *
 * This page explains the purpose of the application and provides
 * a simple way for the user to return to the Student Dashboard.
 */
public class AboutPage {

    /**
     * Builds and returns the About page UI.
     *
     * @param stage the primary JavaFX stage used for navigation
     * @return VBox layout containing the About page content
     */
    public VBox getView(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5F7F6;");

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
                "SmartPark is a campus parking application designed to help students, faculty, and visitors find available parking more efficiently. The system is intended to improve the parking experience by providing users with clear and accessible parking information."
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

        contentBox.getChildren().addAll(paragraph1);

        root.getChildren().addAll(title, subtitle, contentBox, backButton);

        return root;
    }
}