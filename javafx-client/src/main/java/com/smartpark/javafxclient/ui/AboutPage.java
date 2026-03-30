package com.smartpark.javafxclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AboutPage {

    public VBox getView() {
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



        contentBox.getChildren().addAll(paragraph1);

        root.getChildren().addAll(title, subtitle, contentBox);

        return root;
    }
}